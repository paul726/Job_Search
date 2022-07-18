package com.quiz.jobsearch.core.reposiory

open class BaseRepository<T : IRemoteDataSource, R : ILocalDataSource>(
    val remoteDataSource: T,
    val localDataSource: R
) : IRepository

open class BaseRepositoryLocal<T : ILocalDataSource>(
    val remoteDataSource: T
) : IRepository

open class BaseRepositoryRemote<T : IRemoteDataSource>(
    val remoteDataSource: T
) : IRepository

interface IRepository

interface IRemoteDataSource

interface ILocalDataSource