package com.palash.myapplication.api_service

import com.palash.myapplication.models.response.AllEmpListResponse
import com.palash.myapplication.models.response.delete_record.DeleteRecordResponse
import com.palash.myapplication.models.response.new_record.request.NewRecordRequest
import com.palash.myapplication.models.response.new_record.response.NewRecordResponse
import com.palash.myapplication.models.response.record_update.UpdateRecordResponse
import com.palash.myapplication.models.response.single_record_response.SingleEmpRecordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmpAPI {

    @GET("/api/v1/employees")
    suspend fun getEmpRecord(): Response<AllEmpListResponse>

    @GET("/api/v1/employee/{empId}")
    suspend fun singleEmp(@Path("empId") empId: Int): Response<SingleEmpRecordResponse>

    @POST("/api/v1/create")
    suspend fun newRecord(@Body newRecordRequest: NewRecordRequest): Response<NewRecordResponse>

    @PUT("/api/v1/update/{empId}")
    suspend fun updateRecord(@Path("empId") empId: Int, @Body newRecordRequest: NewRecordRequest) : Response<UpdateRecordResponse>

    @DELETE("/api/v1/delete/{empId}")
    suspend fun deleteRecord(@Path("empId") empId: Int) : Response<DeleteRecordResponse>
    
}