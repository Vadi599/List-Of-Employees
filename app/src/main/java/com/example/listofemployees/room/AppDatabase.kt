package com.example.listofemployees.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listofemployees.room.entity.Employee
import com.example.listofemployees.room.dao.EmployeesDao
import com.example.listofemployees.room.entity.EmployeesOfOurCompany


@Database(version = 1, entities = [Employee::class, EmployeesOfOurCompany::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEmployeesDao(): EmployeesDao

}