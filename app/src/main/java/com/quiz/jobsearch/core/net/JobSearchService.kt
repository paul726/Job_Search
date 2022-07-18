package com.quiz.jobsearch.core.net

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface JobSearchService {


    @POST("/")
    fun searchJob(@Body requestBody: RequestBody): Observable<JobSearchData>
}