package com.example.implementdaggerhiltapi.api

import com.example.implementdaggerhiltapi.model.ObjectsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("objects")
    suspend fun getAllObjects(): Response<ObjectsResponse>
}