package com.example.listofemployees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listofemployees.databinding.ListItemEmployeeBinding
import com.example.listofemployees.room.entity.Employee

class EmployeesAdapter(
    private var employeeList: List<Employee?>?,
    private val onEmployeeClick: (Employee?) -> Unit
) :
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

    fun setEmployeesList(employeeList: List<Employee?>?) {
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
        return employeeList!!.size
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        val employee: Employee? = employeeList?.get(position)
        holder.name.text = employee?.employeeName
        holder.salary.text = employee?.employeeSalary.toString()
        holder.age.text = employee?.employeeAge.toString()
        holder.itemView.setOnClickListener {
            onEmployeeClick.invoke(employee)
        }
    }
}