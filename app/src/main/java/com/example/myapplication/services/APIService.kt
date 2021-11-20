package com.example.myapplication.services

import com.example.myapplication.models.JsonDemoResultItem
import retrofit2.Call
import retrofit2.http.GET


interface APIService {
//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?

    @GET("users")
    fun getDemoUsers():Call<List<JsonDemoResultItem>>
}