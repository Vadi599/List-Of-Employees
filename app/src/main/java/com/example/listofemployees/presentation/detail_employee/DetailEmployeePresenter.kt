package com.example.listofemployees.presentation.detail_employee

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.example.listofemployees.model.Employee
import com.example.listofemployees.model.EmployeeResponse
import com.example.listofemployees.network.AppApiClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class DetailEmployeePresenter(private val context: Context) : MvpPresenter<DetailEmployeeView>() {

    private val appApiClient = AppApiClient

    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    private lateinit var obtainedEmployee: Employee

    fun getEmployeeData(id: Long) {
        if (isNetworkAvailable()) {
            viewState.showLoading(true)
            return appApiClient.instance.getEmployee(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<EmployeeResponse?> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(employeeResponse: EmployeeResponse) {
                        if (employeeResponse != null) {
                            obtainedEmployee = employeeResponse.employee
                            viewState.showEmployeeProfile(obtainedEmployee)
                        } else {
                            viewState.showMessage("Ошибка! Пользователь не найден.")
                        }
                    }

                    override fun onError(e: Throwable) {
                        viewState.showMessage("Ошибка! Пользователь не найден." + e.message)
                        viewState.showLoading(false)
                    }
                })
        } else {
            viewState.showMessage("Нет интернета!")
        }
    }

    /*fun getEmployeeDataFromDatabase(id: Long) {
        val employee: Employee = allWorkersRepository.getEmployee(id)
        if (employee == null) {
            viewState.showMessage("Пользователь не найден. ID = $id")
        } else {
            viewState.showEmployeeProfile(employee)
        }
    }*/

    /*fun addToCompanyEmployee() {
        if (obtainedEmployee != null) {
            ourCompanyRepository.insertEmployee(obtainedEmployee)
            viewState.showSuccessfulAddedToCompany()
            viewState.showButtonsState(true)
        }
    }*/

    /*fun deleteFromCompanyEmployee() {
        if (obtainedEmployee != null) {
            ourCompanyRepository.deleteConcreteEmployee(obtainedEmployee.getId())
            viewState.showSuccessfulDeletedFromCompany()
            viewState.showButtonsState(false)
        }
    }*/

    /*fun checkUserExistInOurCompany(employee: Employee) {
        val employeeFromDatabase: Employee = ourCompanyRepository.getEmployee(employee.id)
        val isExistUserInOurCompany = employeeFromDatabase != null
        viewState.showButtonsState(isExistUserInOurCompany)
    }*/

}