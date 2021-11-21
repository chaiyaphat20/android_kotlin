package com.example.myapplication.models


data class ProductResponseItem(
    val created_at: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: Int,
    val stock: Int,
    val updated_at: String
)

data class ProductRequest(
    val name: String,
    val price: Int,
    val stock: Int,
)