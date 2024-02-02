package com.palash.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.myapplication.api_service.EmpAPI
import com.palash.myapplication.models.response.AllEmpListResponse
import com.palash.myapplication.models.response.new_record.request.NewRecordRequest
import com.palash.myapplication.models.response.new_record.response.NewRecordResponse
import com.palash.myapplication.models.response.record_update.UpdateRecordResponse
import com.palash.myapplication.models.response.single_record_response.SingleEmpRecordResponse
import com.palash.myapplication.utils.NetworkResults
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val empAPI: EmpAPI) {

    private var _empRecordLiveData = MutableLiveData<NetworkResults<AllEmpListResponse>>()
    val empRecordLiveData : LiveData<NetworkResults<AllEmpListResponse>> get() = _empRecordLiveData

    suspend fun getAllEmpRecord(){
        _empRecordLiveData.postValue(NetworkResults.Loading())
        val response = empAPI.getEmpRecord()
        if (response.isSuccessful && response.body()!=null){
            _empRecordLiveData.postValue(NetworkResults.Success(response.body()!!))
        }else {
            _empRecordLiveData.postValue(NetworkResults.Error("Server internal error!"))
        }
    }

    //single emp record
    private var _singleEmpRecordLiveData = MutableLiveData<NetworkResults<SingleEmpRecordResponse>>()
    val singleEmpRecordLiveData : LiveData<NetworkResults<SingleEmpRecordResponse>> get() = _singleEmpRecordLiveData

    suspend fun singleEmpRecord(){
        _singleEmpRecordLiveData.postValue(NetworkResults.Loading())
        val response = empAPI.singleEmp(14)
        if (response.isSuccessful && response.body()!=null){
            _singleEmpRecordLiveData.postValue(NetworkResults.Success(response.body()!!))
        }else {
            _singleEmpRecordLiveData.postValue(NetworkResults.Error("Server internal error!"))
        }
    }

    //new emp record
    private var _newEmpRecordLiveData = MutableLiveData<NetworkResults<NewRecordResponse>>()
    val newEmpRecordLiveData : LiveData<NetworkResults<NewRecordResponse>> get() = _newEmpRecordLiveData

    suspend fun newEmpRecord(newRecordRequest: NewRecordRequest){
        _newEmpRecordLiveData.postValue(NetworkResults.Loading())
        val response = empAPI.newRecord(newRecordRequest)
        if (response.isSuccessful && response.body()!=null){
            _newEmpRecordLiveData.postValue(NetworkResults.Success(response.body()!!))
        }else {
            _newEmpRecordLiveData.postValue(NetworkResults.Error("Server internal error!"))
        }
    }

    //update emp record
    private var _updateEmpRecordLiveData = MutableLiveData<NetworkResults<UpdateRecordResponse>>()
    val updateEmpRecordLiveData : LiveData<NetworkResults<UpdateRecordResponse>> get() = _updateEmpRecordLiveData

    suspend fun updateEmpRecord(newRecordRequest: NewRecordRequest){
        _updateEmpRecordLiveData.postValue(NetworkResults.Loading())
        val response = empAPI.updateRecord(1454, newRecordRequest)
        Log.d("MyResss", response.body().toString())
        if (response.isSuccessful && response.body()!=null){
            _updateEmpRecordLiveData.postValue(NetworkResults.Success(response.body()!!))
        }else {
            _updateEmpRecordLiveData.postValue(NetworkResults.Error("Server internal error!"))
        }
    }

    //delete record
    suspend fun deleteRecord(){
        val response = empAPI.deleteRecord(1454)
        Log.d("MyResss", response.body().toString())
        /*if (response.isSuccessful && response.body()!=null){
            _updateEmpRecordLiveData.postValue(NetworkResults.Success(response.body()!!))
        }else {
            _updateEmpRecordLiveData.postValue(NetworkResults.Error("Server internal error!"))
        }*/
    }
}