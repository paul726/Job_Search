package com.quiz.jobsearch.home

import androidx.lifecycle.MutableLiveData
import com.quiz.jobsearch.core.net.JobsData
import com.quiz.jobsearch.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: JobSearchRepository
) : BaseViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val searchResultLiveData = MutableLiveData<JobsData>()

    fun doSearch(content: String) {
        loadingLiveData.postValue(true)
        repo.doSearch(content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadingLiveData.postValue(false)
                it.data?.let { jobsData ->
                    searchResultLiveData.postValue(jobsData)
                }
            }, {
                loadingLiveData.postValue(false)
            })
    }

}