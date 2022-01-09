package com.example.listofemployees.room.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employees")
data class Employee(

    @SerializedName("employee_age")
    @ColumnInfo(name = "employee_age")
    var employeeAge: String,

    @SerializedName("employee_name")
    @ColumnInfo(name = "employee_name")
    var employeeName: String,

    @SerializedName("employee_salary")
    @ColumnInfo(name = "employee_salary")
    var employeeSalary: String,


    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long

)
