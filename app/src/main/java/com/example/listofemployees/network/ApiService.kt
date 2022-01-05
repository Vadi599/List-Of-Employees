package com.example.listofemployees.network

import com.example.listofemployees.model.EmployeeDeleteResponse
import com.example.listofemployees.model.EmployeeResponse
import com.example.listofemployees.model.EmployeesResponse
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("employees")
    fun getEmployees(): Single<EmployeesResponse?>

    @GET("employee/{id}")
    fun getEmployee(
        @Path("id") id: Long
    ): Single<EmployeeResponse?>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Long): Single<EmployeeDeleteResponse?>
}