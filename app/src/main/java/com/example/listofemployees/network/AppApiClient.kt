package com.example.listofemployees.network

import com.example.listofemployees.model.EmployeeResponse
import com.example.listofemployees.model.EmployeesResponse
import io.reactivex.Single

class AppApiClient {

    companion object {
        var instance = AppApiClient()
        fun get(): AppApiClient {
            return instance
        }
    }

    fun getEmployees(): Single<EmployeesResponse?> {
        return ServiceGenerator.apiService
            .getEmployees()
    }

    fun getEmployee(id: Long): Single<EmployeeResponse?> {
        return ServiceGenerator.apiService
            .getEmployee(id)
    }
}