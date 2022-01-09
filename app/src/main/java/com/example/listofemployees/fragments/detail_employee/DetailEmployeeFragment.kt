package com.example.listofemployees.fragments.detail_employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.listofemployees.databinding.FragmentDetailEmployeeBinding
import com.example.listofemployees.presentation.detail_employee.DetailEmployeePresenter
import com.example.listofemployees.presentation.detail_employee.DetailEmployeeView
import com.example.listofemployees.room.entity.Employee
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailEmployeeFragment : MvpAppCompatFragment(), DetailEmployeeView {

    private lateinit var binding: FragmentDetailEmployeeBinding

    private val presenter by moxyPresenter { DetailEmployeePresenter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEmployeeBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        val view = binding.root
        val employeeId = arguments?.getLong("employeeId")
        employeeId?.let { presenter.getEmployeeData(it) }
        return view
    }

    override fun showEmployeeProfile(employee: Employee?) {
        binding.tvName.text = employee?.employeeName
        binding.tvSalary.text = employee?.employeeSalary.toString()
        binding.tvAge.text = employee?.employeeAge.toString()
        showLoading(false)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(show: Boolean) {
        binding.progressView.isVisible = show
    }

}