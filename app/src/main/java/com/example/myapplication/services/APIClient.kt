package com.example.myapplication.services

import com.example.myapplication.BASE_URL
import com.example.myapplication.IMAGE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    private var retrofit: Retrofit? = null

    //Singleton Pattern
    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //เอา JSONDemoResult convert เป็น JSON
                .build()
        }
        return retrofit!!
    }

    fun getImageUrl() = "${BASE_URL}/${IMAGE_URL}/"

}