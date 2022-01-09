package com.example.listofemployees.presentation.our_company

import android.content.Context
import com.example.listofemployees.presentation.detail_employee.DetailEmployeePresenter
import com.example.listofemployees.room.entity.EmployeesOfOurCompany
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class OurCompanyPresenter(context: Context) : MvpPresenter<OurCompanyView>() {

    private val detailEmployeePresenter = DetailEmployeePresenter(context)

    fun getOurCompanyInfo() {
        val employees: List<EmployeesOfOurCompany?> =
            detailEmployeePresenter.mainPresenter.employeesDao.getAllEmployeesOfOurCompany()
        viewState.showEmployees(employees)
    }

    fun editEmployee(employee: EmployeesOfOurCompany?) {
        detailEmployeePresenter.mainPresenter.employeesDao.updateEmployee(employee)
        getOurCompanyInfo()
    }

    fun addEmployee(employee: EmployeesOfOurCompany?) {
        detailEmployeePresenter.mainPresenter.employeesDao.insertEmployee(employee)
        getOurCompanyInfo()
    }

    fun deleteFromOurCompanyEmployee(id: Long?) {
        detailEmployeePresenter.mainPresenter.employeesDao.deleteFromOurCompanyEmployee(id)
        getOurCompanyInfo()
    }

}