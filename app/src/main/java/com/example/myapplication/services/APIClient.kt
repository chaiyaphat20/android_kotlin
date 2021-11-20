package com.example.myapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    private var retrofit: Retrofit? = null

    //Singleton Pattern
    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()) //เอา JSONDemoResult convert เป็น JSON
                .build()
        }
        return retrofit!!
    }
}