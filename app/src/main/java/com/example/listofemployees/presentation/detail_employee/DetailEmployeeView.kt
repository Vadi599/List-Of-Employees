package com.example.listofemployees.presentation.detail_employee

import com.example.listofemployees.room.entity.Employee
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailEmployeeView : MvpView {

    fun showEmployeeProfile(employee: Employee?)

    fun showMessage(message: String?)

    fun showLoading(show: Boolean)

}