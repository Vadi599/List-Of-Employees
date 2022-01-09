package com.example.listofemployees.room.dao

import androidx.room.*
import com.example.listofemployees.room.entity.Employee
import com.example.listofemployees.room.entity.EmployeesOfOurCompany


@Dao
interface EmployeesDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE id=:employeeId")
    fun findEmployeeById(employeeId: Long): Employee

    @Query("SELECT * FROM employees_of_our_company")
    fun getAllEmployeesOfOurCompany(): List<EmployeesOfOurCompany?>

    @Update
    fun updateEmployee(employee: EmployeesOfOurCompany?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeesOfOurCompany?)

    @Query("DELETE FROM employees_of_our_company WHERE id=:employeeId")
    fun deleteFromOurCompanyEmployee(employeeId: Long?)

}