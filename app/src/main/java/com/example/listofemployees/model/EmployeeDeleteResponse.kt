package com.example.listofemployees.model


import com.google.gson.annotations.SerializedName

data class EmployeeDeleteResponse(
    @SerializedName("data")
    val data: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)