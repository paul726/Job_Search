package com.quiz.jobsearch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {
    var queryTemplate = ""
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        val byteArray = resources?.assets?.open("query_template")?.readBytes()
        queryTemplate = byteArray?.let { String(it) } ?: ""
    }

    companion object {
        lateinit var INSTANCE: AppApplication
    }
}