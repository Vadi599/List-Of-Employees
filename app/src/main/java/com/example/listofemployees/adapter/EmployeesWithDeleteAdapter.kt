package com.example.listofemployees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listofemployees.databinding.ListItemEmployeeWithDeleteBinding
import com.example.listofemployees.model.Employee

class EmployeesWithDeleteAdapter(private var employeeList: List<Employee?>) :
    RecyclerView.Adapter<EmployeesWithDeleteAdapter.EmployeesWithDeleteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeesWithDeleteViewHolder {
        val binding =
            ListItemEmployeeWithDeleteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return EmployeesWithDeleteViewHolder(
            binding
        )
    }

    fun setEmployeesList(employeeList: List<Employee?>) {
        this.employeeList = employeeList
        notifyDataSetChanged()
    }

    class EmployeesWithDeleteViewHolder(binding: ListItemEmployeeWithDeleteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.tvName
        var salary: TextView = binding.tvSalary
        var age: TextView = binding.tvAge
        val btnDelete: ImageButton = binding.btnImageDelete
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: EmployeesWithDeleteViewHolder, position: Int) {
        holder.name.text = employeeList[position]?.employeeName
        holder.salary.text = employeeList[position]?.employeeSalary.toString()
        holder.age.text = employeeList[position]?.employeeAge.toString()
        holder.btnDelete.setOnClickListener {

        }
        holder.itemView.setOnClickListener {

        }

    }

}