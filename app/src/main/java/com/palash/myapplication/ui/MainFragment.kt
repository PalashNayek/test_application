package com.palash.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.palash.myapplication.R
import com.palash.myapplication.adapter.EmployeeListAdapter
import com.palash.myapplication.databinding.FragmentMainBinding
import com.palash.myapplication.utils.NetworkResults
import com.palash.myapplication.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: EmployeeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = EmployeeListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reccyerView.layoutManager = LinearLayoutManager(context)
        binding.reccyerView.setHasFixedSize(true)
        binding.reccyerView.adapter = adapter

        /*viewModel.empResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResults.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is NetworkResults.Success -> {
                    adapter.submitList(it.data!!.data)
                }
                is NetworkResults.Error -> {
                    binding.txtError.text = it.error_smg
                }
            }
        })*/

        viewModel.singleEmpResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            binding.txtError.isVisible = false
            when (it) {
                is NetworkResults.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is NetworkResults.Success -> {
                    binding.txtError.isVisible = true
                    binding.txtError.text = it.data!!.data.employee_name
                    Log.d("MyRes", it.data.data.employee_name)
                }
                is NetworkResults.Error -> {
                    binding.txtError.isVisible = true
                    binding.txtError.text = it.error_smg
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}