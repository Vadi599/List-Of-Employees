package com.example.listofemployees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listofemployees.databinding.ListItemEmployeesOfOurCompanyBinding
import com.example.listofemployees.room.entity.EmployeesOfOurCompany

class EmployeesOfOurCompanyAdapter(
    private var employeeList: List<EmployeesOfOurCompany?>?,
    private val onEmployeeClick: (EmployeesOfOurCompany?) -> Unit,
    private val onDeleteClick: (EmployeesOfOurCompany?) -> Unit
) :
    RecyclerView.Adapter<EmployeesOfOurCompanyAdapter.EmployeesOfOurCompanyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeesOfOurCompanyViewHolder {
        val binding =
            ListItemEmployeesOfOurCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return EmployeesOfOurCompanyViewHolder(
            binding
        )
    }

    fun setEmployeesList(employeeList: List<EmployeesOfOurCompany?>?) {
        this.employeeList = employeeList
        notifyDataSetChanged()
    }

    class EmployeesOfOurCompanyViewHolder(binding: ListItemEmployeesOfOurCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name: TextView? = binding.tvName
        var salary: TextView? = binding.tvSalary
        var age: TextView? = binding.tvAge
        val btnDelete: ImageButton = binding.btnImageDelete
    }

    override fun getItemCount(): Int {
        return employeeList!!.size
    }

    override fun onBindViewHolder(holder: EmployeesOfOurCompanyViewHolder, position: Int) {
        val employee: EmployeesOfOurCompany? = employeeList?.get(position)
        holder.name?.text = employeeList?.get(position)?.employeeName
        holder.salary?.text = employeeList?.get(position)?.employeeSalary.toString()
        holder.age?.text = employeeList?.get(position)?.employeeAge.toString()
        holder.btnDelete.setOnClickListener {
            onDeleteClick.invoke(employee)
        }
        holder.itemView.setOnClickListener {
            onEmployeeClick.invoke(employee)
        }

    }

}