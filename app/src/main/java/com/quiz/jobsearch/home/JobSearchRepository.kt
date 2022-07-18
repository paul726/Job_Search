package com.quiz.jobsearch.home

import com.quiz.jobsearch.core.net.JobSearchData
import com.quiz.jobsearch.core.net.JobSearchService
import com.quiz.jobsearch.core.reposiory.BaseRepository
import com.quiz.jobsearch.core.reposiory.ILocalDataSource
import com.quiz.jobsearch.core.reposiory.IRemoteDataSource
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

class JobSearchRepository @Inject constructor(
    remoteDataSource: JobSearchRemoteDataSource,
    localDataSource: JobSearchLocalDataSource
) : BaseRepository<JobSearchRemoteDataSource, JobSearchLocalDataSource>(
    remoteDataSource,
    localDataSource
) {

    fun doSearch(content: String): Observable<JobSearchData> {
        return remoteDataSource.doSearch(content)
    }
}

class JobSearchRemoteDataSource @Inject constructor(private val jobSearchService: JobSearchService) :
    IRemoteDataSource {

    fun doSearch(content: String): Observable<JobSearchData> {
//        val queryTemplate = "{\n" +
//                "  \"operationName\": null,\n" +
//                "  \"variables\": {},\n" +
//                "  \"query\": \"{\n  jobs(input: {type: \"developer\"}) {\n    title\n    company {\n      name\n      logoUrl\n    }\n    description\n  }\n}\"" +
//                "\n}".replace("developer", content)
        val mediaType = "application/json,charset=utf-8".toMediaTypeOrNull();
        val requestBody = RequestBody.create(mediaType, content)
        return jobSearchService.searchJob(requestBody)
    }
}

class JobSearchLocalDataSource @Inject constructor() : ILocalDataSource