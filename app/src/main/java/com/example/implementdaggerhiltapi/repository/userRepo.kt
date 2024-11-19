package com.example.implementdaggerhiltapi.repository

import com.example.implementdaggerhiltapi.api.ApiServices
import com.example.implementdaggerhiltapi.model.ObjectsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class userRepo @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getObjects(): ObjectsResponse? {
        return try {
            val response = apiServices.getAllObjects()
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
}