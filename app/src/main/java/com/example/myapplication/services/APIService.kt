package com.example.myapplication.services

import com.example.myapplication.API_PRODUCT
import com.example.myapplication.API_PRODUCT_PARAMS_ID
import com.example.myapplication.models.ProductRequest
import com.example.myapplication.models.ProductResponseItem
import retrofit2.Call
import retrofit2.http.*


interface APIService {
//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?

    @GET(API_PRODUCT)
    fun getProducts(): Call<List<ProductResponseItem>>

    @POST(API_PRODUCT)
    fun addProduct(@Body product: ProductRequest): Call<Any>

    //xxxx/product/44
    @PUT("$API_PRODUCT/$API_PRODUCT_PARAMS_ID")
    fun editProduct(
        @Path(API_PRODUCT_PARAMS_ID) id: Int,
        @Body product: ProductRequest,
    ): Call<Any>

    @DELETE("$API_PRODUCT/$API_PRODUCT_PARAMS_ID")
    fun deleteProduct(
        @Path(API_PRODUCT_PARAMS_ID) id: Int,
    ): Call<Any>

}