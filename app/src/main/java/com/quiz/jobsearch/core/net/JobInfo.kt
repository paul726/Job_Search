package com.quiz.jobsearch.core.net

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JobSearchData(@SerializedName("data") val data: JobsData?)

data class JobsData(@SerializedName("jobs") val jobs: List<JobInfo?>?) : Serializable

data class JobInfo(
    @SerializedName("title") val title: String?,
    @SerializedName("company") val company: Company?,
    @SerializedName("description") val description: String?
) : Serializable

data class Company(
    @SerializedName("name") val name: String?,
    @SerializedName("logoUrl") val logoUrl: String?
) : Serializable