package com.example.listofemployees.model

import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("data")
    val employees: Employee,
    @SerializedName("status")
    val status: String
)