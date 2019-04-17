package com.boni.data.repository

import com.boni.data.api.AuthApi
import com.boni.data.mapper.UserMapper
import com.boni.domain.AuthRepository
import com.boni.domain.entities.UserEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AuthRepositoryImpl (
    private val authApi: AuthApi,
    private val mapper: UserMapper
) : AuthRepository {

    override fun getUser(
        email: String,
        name: String
    ): Observable<UserEntity> {
        return authApi.authenticate(
            email = email,
            name = name
        ).subscribeOn(Schedulers.io())
            .map {
                mapper.mapFromModel(it.user)
            }
    }
}