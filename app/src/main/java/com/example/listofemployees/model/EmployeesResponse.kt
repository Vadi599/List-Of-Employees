package com.example.listofemployees.model

import com.example.listofemployees.room.entity.Employee
import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @SerializedName("data")
    val employees: List<Employee>,
    @SerializedName("status")
    val status: String
)
