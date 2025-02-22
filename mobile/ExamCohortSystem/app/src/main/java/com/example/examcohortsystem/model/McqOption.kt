package com.example.examcohortsystem.model

import com.google.gson.annotations.SerializedName

data class McqOption(
    @SerializedName("isSelectedInAnswer") var isSelectedInAnswer: Boolean,
    @SerializedName("mcqOptionID") val mcqOptionID: Int,
    @SerializedName("mcqOptionText") val mcqOptionText: String
)