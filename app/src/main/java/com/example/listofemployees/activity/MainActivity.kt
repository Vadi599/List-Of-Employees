package com.example.listofemployees.activity

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.listofemployees.adapter.EmployeesAdapter
import com.example.listofemployees.databinding.ActivityMainBinding
import com.example.listofemployees.model.Employee
import com.example.listofemployees.presentation.MainPresenter
import com.example.listofemployees.presentation.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: EmployeesAdapter

    private lateinit var employeeList: List<Employee>

    private val presenter by moxyPresenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        employeeList = ArrayList()
        adapter = EmployeesAdapter(employeeList)
        binding.rvEmployees.adapter = adapter
        presenter.getDataFromServer()
    }

    override fun showEmployees(employees: List<Employee?>) {
        adapter.setEmployeesList(employees)
        showLoading(false)
    }

    override fun showConcreteEmployee(employee: Employee?) {

    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(show: Boolean) {
        binding.progressView.isVisible = show
    }

}