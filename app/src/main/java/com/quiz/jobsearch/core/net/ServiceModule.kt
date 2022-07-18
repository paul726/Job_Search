package com.quiz.jobsearch.core.net

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideJobSearchService(retrofit: Retrofit): JobSearchService {
        return retrofit.create(JobSearchService::class.java)
    }
}

