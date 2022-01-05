package com.example.listofemployees.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.listofemployees.databinding.FragmentProfileBinding
import com.example.listofemployees.model.Employee
import com.example.listofemployees.presentation.detail_employee.DetailEmployeePresenter
import com.example.listofemployees.presentation.detail_employee.DetailEmployeeView
import com.example.listofemployees.presentation.main.MainPresenter
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailEmployeeFragment : MvpAppCompatFragment(), DetailEmployeeView {

    private lateinit var binding: FragmentProfileBinding

    private val presenter by moxyPresenter { DetailEmployeePresenter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        val view = binding.root
        val employeeId = arguments?.getLong("employeeId")
        if (employeeId != null) {
            presenter.getEmployeeData(employeeId)
        }
        return view
    }

    override fun showEmployeeProfile(employee: Employee?) {
        binding.tvName.text = employee?.employeeName
        binding.tvSalary.text = employee?.employeeSalary.toString()
        binding.tvAge.text = employee?.employeeAge.toString()
        // visibility flags
        // VISIBLE - view видимо
        // INVISIBLE - view не видимо, но контент остается на макете
        // GONE - view не видимо, контент исчезает из макета
        // visibility flags
        // VISIBLE - view видимо
        // INVISIBLE - view не видимо, но контент остается на макете
        // GONE - view не видимо, контент исчезает из макета
        //presenter.checkUserExistInOurCompany(employee)

        showLoading(false)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /* override fun showSuccessfulAddedToCompany() {
         Snackbar.make(
             binding.btnAddToOurCompany,
             "Успешно добавлен пользователь в компанию",
             Snackbar.LENGTH_SHORT
         )
             .setAction("Список сотрудников") { showOurCompanyActivity() }.show()
     }

     override fun showSuccessfulDeletedFromCompany() {
         Snackbar.make(
             binding.btnAddToOurCompany,
             "Успешно удалён пользователь из компанию",
             Snackbar.LENGTH_SHORT
         )
             .setAction("Список сотрудников") { showOurCompanyActivity() }.show()
     }*/

    override fun showButtonsState(isExistUserInOurCompany: Boolean) {
        if (isExistUserInOurCompany) {
            binding.btnAddToOurCompany.isVisible = false
            binding.btnDeleteToOurCompany.isVisible = true
        } else {
            binding.btnAddToOurCompany.isVisible = true
            binding.btnDeleteToOurCompany.isVisible = false
        }
    }

    override fun showLoading(show: Boolean) {
        binding.progressView.isVisible = show
    }
}