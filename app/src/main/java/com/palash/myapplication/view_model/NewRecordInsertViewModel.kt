package com.palash.myapplication.view_model

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.myapplication.models.response.new_record.request.NewRecordRequest
import com.palash.myapplication.models.response.new_record.response.NewRecordResponse
import com.palash.myapplication.repository.EmployeeRepository
import com.palash.myapplication.utils.NetworkResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewRecordInsertViewModel @Inject constructor(private val employeeRepository: EmployeeRepository) : ViewModel() {

    val newRecordInsertResponseLiveData : LiveData<NetworkResults<NewRecordResponse>> get() = employeeRepository.newEmpRecordLiveData

    init {
        viewModelScope.launch {
            //employeeRepository.updateEmpRecord(NewRecordRequest("20", "Palash", "25000"))
            employeeRepository.deleteRecord()
        }
    }

    fun newRecordInsert(newRecordRequest: NewRecordRequest){
        viewModelScope.launch {
            employeeRepository.newEmpRecord(newRecordRequest)
        }
    }

    fun validationForm(age : String, name : String, salary : String) : Pair<Boolean, String>{
        var result = Pair(true, "")
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(salary) || TextUtils.isEmpty(age))
        {
            result = Pair(false, "Please all credential fill-up")
        }
        return result
    }
}