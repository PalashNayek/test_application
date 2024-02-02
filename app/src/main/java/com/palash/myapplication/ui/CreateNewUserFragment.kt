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
import androidx.navigation.fragment.findNavController
import com.palash.myapplication.R
import com.palash.myapplication.databinding.FragmentCreateNewUserBinding
import com.palash.myapplication.models.response.new_record.request.NewRecordRequest
import com.palash.myapplication.utils.NetworkResults
import com.palash.myapplication.view_model.NewRecordInsertViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewUserFragment : Fragment() {
    private var _binding: FragmentCreateNewUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NewRecordInsertViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            val validationResult = credential()
            if (validationResult.first){
                viewModel.newRecordInsert(newRecordInsert())
            }else{
                binding.txtError.text=validationResult.second
            }
        }

        viewModel.newRecordInsertResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when(it){
                is NetworkResults.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is NetworkResults.Success -> {
                    Log.d("MyRes", it.data!!.message)
                    binding.txtError.text=it.data.message
                    //findNavController().navigate(R.id.ac)
                }
                is NetworkResults.Error -> {
                    binding.txtError.text=it.error_smg
                }
            }
        })
    }

    private fun newRecordInsert() : NewRecordRequest{
        val name = binding.edName.text.toString()
        val salary = binding.edSalary.text.toString()
        val age = binding.edAge.text.toString()
        return NewRecordRequest(age, name, salary)
    }

    private fun credential() : Pair<Boolean, String>{
        val newRequest = newRecordInsert()
        return viewModel.validationForm(newRequest.age, newRequest.name, newRequest.salary)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}