package com.palash.myapplication.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.myapplication.models.response.AllEmpListResponse
import com.palash.myapplication.models.response.single_record_response.SingleEmpRecordResponse
import com.palash.myapplication.repository.EmployeeRepository
import com.palash.myapplication.utils.NetworkResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val employeeRepository: EmployeeRepository) : ViewModel() {
    val empResponseLiveData : LiveData<NetworkResults<AllEmpListResponse>> get() = employeeRepository.empRecordLiveData
    val singleEmpResponseLiveData : LiveData<NetworkResults<SingleEmpRecordResponse>> get() = employeeRepository.singleEmpRecordLiveData

    init {
        viewModelScope.launch {
           // employeeRepository.getAllEmpRecord()
            employeeRepository.singleEmpRecord()
        }
    }
}