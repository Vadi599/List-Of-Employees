package com.example.listofemployees.fragments.our_company

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.listofemployees.R
import com.example.listofemployees.adapter.EmployeesOfOurCompanyAdapter
import com.example.listofemployees.databinding.CustomDialogAddEmployeeBinding
import com.example.listofemployees.databinding.CustomDialogDeleteFromOurCompanyBinding
import com.example.listofemployees.databinding.CustomDialogEditEmployeeBinding
import com.example.listofemployees.databinding.FragmentOurCompanyBinding
import com.example.listofemployees.presentation.our_company.OurCompanyPresenter
import com.example.listofemployees.presentation.our_company.OurCompanyView
import com.example.listofemployees.room.entity.EmployeesOfOurCompany
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class OurCompanyFragment : MvpAppCompatFragment(), OurCompanyView {

    private lateinit var binding: FragmentOurCompanyBinding

    private lateinit var employeeList: List<EmployeesOfOurCompany?>

    private val employeesOfOurCompanyAdapter by lazy {
        EmployeesOfOurCompanyAdapter(
            employeeList, this::onEmployeeClick, this::onDeleteClick
        )
    }

    private val presenter by moxyPresenter { OurCompanyPresenter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOurCompanyBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        val view = binding.root
        setHasOptionsMenu(true)
        employeeList = ArrayList()
        binding.rvEmployees.adapter = employeesOfOurCompanyAdapter
        presenter.getOurCompanyInfo()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.action_show_company).isVisible = false
        menu.findItem(R.id.action_create_employee).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_employee -> {
                addEmployee()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addEmployee() {
        val customDialogAddEmployeeBinding: CustomDialogAddEmployeeBinding =
            CustomDialogAddEmployeeBinding.inflate(
                layoutInflater
            )
        customDialogAddEmployeeBinding.tvTitle.setText(R.string.add_employee)
        customDialogAddEmployeeBinding.tvEmployeeName.setText(R.string.employee_add_name)
        customDialogAddEmployeeBinding.tvEmployeeSalary.setText(R.string.employee_add_salary)
        customDialogAddEmployeeBinding.tvEmployeeAge.setText(R.string.employee_add_age)
        val customAlertBuilder: AlertDialog = AlertDialog.Builder(requireContext())
            .setView(customDialogAddEmployeeBinding.root)
            .create()
        customDialogAddEmployeeBinding.btnAddCancel.setOnClickListener { customAlertBuilder.dismiss() }
        customDialogAddEmployeeBinding.btnAddOk.setOnClickListener {
            val name: String = customDialogAddEmployeeBinding.etName.text.toString()
            val age: String = customDialogAddEmployeeBinding.etAge.text.toString()
            val salary: String = customDialogAddEmployeeBinding.etSalary.text.toString()
            if (name.isEmpty() || age.isEmpty() || salary.isEmpty()) {
                showMessage("Введите данные всех полей!")
            } else {
                val employee = EmployeesOfOurCompany(age, name, salary, null)
                presenter.addEmployee(employee)
                customAlertBuilder.dismiss()
            }
        }
        customAlertBuilder.show()
    }

    private fun deleteDialog(employee: EmployeesOfOurCompany?) {
        val customDialogDeleteFromOurCompanyBinding: CustomDialogDeleteFromOurCompanyBinding =
            CustomDialogDeleteFromOurCompanyBinding.inflate(
                layoutInflater
            )
        customDialogDeleteFromOurCompanyBinding.tvEmployeeTitle.setText(R.string.delete_employee_from_our_company)
        customDialogDeleteFromOurCompanyBinding.tvEmployeeDescription.setText(R.string.delete_employee_from_this_company)
        val customAlertBuilder: AlertDialog = AlertDialog.Builder(requireContext())
            .setView(customDialogDeleteFromOurCompanyBinding.root)
            .create()
        customDialogDeleteFromOurCompanyBinding.btnDeleteCancel.setOnClickListener { customAlertBuilder.dismiss() }
        customDialogDeleteFromOurCompanyBinding.btnDeleteOk.setOnClickListener {
            employee?.id?.let { it1 -> presenter.deleteFromOurCompanyEmployee(it1) }
            customAlertBuilder.dismiss()
        }
        customAlertBuilder.show()
    }

    private fun editEmployee(employee: EmployeesOfOurCompany?) {
        val customDialogEditEmployeeBinding: CustomDialogEditEmployeeBinding =
            CustomDialogEditEmployeeBinding.inflate(
                layoutInflater
            )
        customDialogEditEmployeeBinding.tvTitle.setText(R.string.edit_employee)
        customDialogEditEmployeeBinding.tvEmployeeName.setText(R.string.employee_name)
        customDialogEditEmployeeBinding.etName.setText(employee?.employeeName)
        customDialogEditEmployeeBinding.tvEmployeeSalary.setText(R.string.employee_salary)
        customDialogEditEmployeeBinding.etSalary.setText(employee?.employeeSalary)
        customDialogEditEmployeeBinding.tvEmployeeAge.setText(R.string.employee_age)
        customDialogEditEmployeeBinding.etAge.setText(employee?.employeeAge)
        val customAlertBuilder: AlertDialog = AlertDialog.Builder(requireContext())
            .setView(customDialogEditEmployeeBinding.root)
            .create()
        customDialogEditEmployeeBinding.btnEditCancel.setOnClickListener { customAlertBuilder.dismiss() }
        customDialogEditEmployeeBinding.btnEditOk.setOnClickListener {
            val parsedName: String =
                customDialogEditEmployeeBinding.etName.text.toString()
            val parsedSalary: String =
                customDialogEditEmployeeBinding.etSalary.text.toString()
            val parsedAge: String =
                customDialogEditEmployeeBinding.etAge.text.toString()
            employee?.employeeAge = parsedAge
            employee?.employeeSalary = parsedSalary
            employee?.employeeName = parsedName
            presenter.editEmployee(employee)
            customAlertBuilder.dismiss()
        }
        customAlertBuilder.show()
    }

    override fun showEmployees(employees: List<EmployeesOfOurCompany?>?) {
        if (employees?.size == 0) {
            binding.viewFlipper.displayedChild = 1
        } else {
            binding.viewFlipper.displayedChild = 0
            employeesOfOurCompanyAdapter.setEmployeesList(employees)
        }
    }

    override fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun onEmployeeClick(employee: EmployeesOfOurCompany?) {
        editEmployee(employee)
    }

    private fun onDeleteClick(employee: EmployeesOfOurCompany?) {
        deleteDialog(employee)
    }

}