package com.example.examcohortsystem.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examcohortsystem.model.AssessmentResponse
import com.example.examcohortsystem.services.ExamCohortApiService
import com.example.examcohortsystem.utils.datastore.StoreJwtToken
import kotlinx.coroutines.launch

class AssessmentListViewModel : ViewModel() {
    var assessmentResponse: MutableLiveData<AssessmentResponse> = MutableLiveData()


    fun getAssessments(jwtToken: String, cohortId: String) {
        val apiService = ExamCohortApiService()

        viewModelScope.launch {
            val assessmentRes =
                apiService.getAssessments(cohortId = cohortId, jwtToken = jwtToken, res
                = {
                    assessmentResponse.value = it
                    Log.d(TAG, "getCohorts: ${assessmentResponse.value}")
//                _cohorts.value = it
                })

//            examCohortResponse.value = apiService.getExamCohorts()
        }
    }

}