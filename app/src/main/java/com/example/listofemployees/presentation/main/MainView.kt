package com.example.listofemployees.presentation.main

import com.example.listofemployees.model.Employee
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun showEmployees(employees: List<Employee?>)

    fun showMessage(message: String?)

    fun showLoading(show: Boolean)

}