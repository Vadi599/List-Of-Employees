package com.example.listofemployees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listofemployees.databinding.ListItemEmployeeBinding
import com.example.listofemployees.model.Employee

class EmployeesAdapter(private var employeeList: List<Employee?>) :
    RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeesViewHolder {
        val binding =
            ListItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeesViewHolder(
            binding
        )
    }

    fun setEmployeesList(employeeList: List<Employee?>) {
        this.employeeList = employeeList
        notifyDataSetChanged()
    }

    class EmployeesViewHolder(binding: ListItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.tvName
        var salary: TextView = binding.tvSalary
        var age: TextView = binding.tvAge
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        holder.name.text = employeeList[position]?.employeeName
        holder.salary.text = employeeList[position]?.employeeSalary.toString()
        holder.age.text = employeeList[position]?.employeeAge.toString()
    }

}