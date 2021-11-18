package com.example.myapplication

import android.content.Context
import android.widget.Toast

//การพิมพ์ Context. เป็นการขยายความสามารถของ Context
// this อันนี้ หมายถึง Context
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
}

fun String.convertToBath(): String {
    return "$this Bath"
}