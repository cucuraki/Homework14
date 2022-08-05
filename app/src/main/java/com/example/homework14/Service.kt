package com.example.homework14

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("f4864c66-ee04-4e7f-88a2-2fbd912ca5ab")
    suspend fun getInfo(): Response<Data>
}