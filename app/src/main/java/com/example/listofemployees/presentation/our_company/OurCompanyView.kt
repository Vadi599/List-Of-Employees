package com.example.listofemployees.presentation.our_company

import com.example.listofemployees.room.entity.EmployeesOfOurCompany
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface OurCompanyView : MvpView {

    fun showEmployees(employees: List<EmployeesOfOurCompany?>?)

    fun showMessage(message: String?)

}