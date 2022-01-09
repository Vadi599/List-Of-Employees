package com.example.listofemployees.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees_of_our_company")
data class EmployeesOfOurCompany(
    @ColumnInfo(name = "employee_age")
    var employeeAge: String?,

    @ColumnInfo(name = "employee_name")
    var employeeName: String?,

    @ColumnInfo(name = "employee_salary")
    var employeeSalary: String?,

    @PrimaryKey(autoGenerate = true)
    val id: Long?
)