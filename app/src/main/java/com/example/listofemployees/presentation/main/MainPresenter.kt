package com.example.listofemployees.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.listofemployees.room.entity.Employee
import com.example.listofemployees.model.EmployeesResponse
import com.example.listofemployees.network.AppApiClient
import com.example.listofemployees.room.AppDatabase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class MainPresenter(private val context: Context) : MvpPresenter<MainView>() {

    private val appApiClient = AppApiClient

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "list-of-employees"
    ).allowMainThreadQueries().build()

    val employeesDao = db.getEmployeesDao()

    private fun getDataFromDatabase() {
        val employees: List<Employee?>? =
            employeesDao.getAllEmployees()
        if (employees?.size == 0) {
            viewState.showMessage("Нет данных в БД. Включите интернет и перезапустите приложение")
        } else {
            viewState.showEmployees(employees)
        }
    }

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

                    override fun onSuccess(employeesResponse: EmployeesResponse) {
                        val employeeList: List<Employee> = employeesResponse.employees
                        for (employee in employeeList) {
                            employeesDao.insertEmployee(employee)
                        }
                        viewState.showEmployees(employeeList)
                    }

                    override fun onError(e: Throwable) {
                        viewState.showMessage("Ошибка!Работники не найдены!" + e.message)
                        viewState.showLoading(false)
                    }
                })
        } else {
            viewState.showMessage("Нет интернета!Берем данные из БД")
            getDataFromDatabase()
        }
    }
}
