package com.example.listofemployees.model

import com.example.listofemployees.room.entity.Employee
import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("data")
    val employee: Employee,
    @SerializedName("status")
    val status: String
)