package com.example.listofemployees.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.example.listofemployees.model.Employee
import com.example.listofemployees.model.EmployeesResponse
import com.example.listofemployees.network.AppApiClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class MainPresenter(private val context: Context) : MvpPresenter<MainView>() {

    private val appApiClient = AppApiClient

    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    fun getDataFromServer() {
        if (isNetworkAvailable()) {
            viewState.showLoading(true)
            return appApiClient.instance.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<EmployeesResponse?> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: EmployeesResponse) {
                        val employeeList: List<Employee> = t.employees
                        //  repository.deleteAllRows()
                        for (employee in employeeList) {
                            // repository.insertEmployee(employee)
                        }
                        viewState.showEmployees(employeeList)
                    }

                    override fun onError(e: Throwable) {
                        viewState.showMessage(e.message)
                        viewState.showLoading(false)
                    }
                })
        } else {
            viewState.showMessage("Нет интернета!")
        }
    }
}
