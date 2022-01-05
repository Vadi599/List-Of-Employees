package com.example.listofemployees.main

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.listofemployees.R
import com.example.listofemployees.adapter.EmployeesAdapter
import com.example.listofemployees.databinding.ActivityMainBinding
import com.example.listofemployees.fragments.DetailEmployeeFragment
import com.example.listofemployees.model.Employee
import com.example.listofemployees.presentation.main.MainPresenter
import com.example.listofemployees.presentation.main.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var employeeList: List<Employee>

    private val employeesAdapter by lazy {
        EmployeesAdapter(
            employeeList, this::onEmployeeClick
        )
    }

    private val presenter by moxyPresenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        employeeList = ArrayList()
        binding.rvEmployees.adapter = employeesAdapter
        presenter.getDataFromServer()

    }

    override fun showEmployees(employees: List<Employee?>) {
        employeesAdapter.setEmployeesList(employees)
        showLoading(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.action_create_employee).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(show: Boolean) {
        binding.progressView.isVisible = show
    }

    private fun onEmployeeClick(employee: Employee?) {
        val bundle = Bundle()
        employee?.id?.let { bundle.putLong("employeeId", it) }
        val fragment = DetailEmployeeFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}