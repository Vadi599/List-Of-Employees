package com.example.listofemployees.network

import com.example.listofemployees.model.EmployeeDeleteResponse
import com.example.listofemployees.model.EmployeeResponse
import com.example.listofemployees.model.EmployeesResponse
import com.example.listofemployees.network.ServiceGenerator.apiService
import io.reactivex.Single

class AppApiClient(private val apiService: ApiService) {

    companion object {
        var instance = AppApiClient(apiService)
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

    fun deleteEmployee(id: Long): Single<EmployeeDeleteResponse?> {
        return ServiceGenerator.apiService
            .deleteEmployee(id)
    }
}