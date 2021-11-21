package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCreateActicityBinding
import com.example.myapplication.models.ProductRequest
import com.example.myapplication.services.APIClient
import com.example.myapplication.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateActicityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateActicityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWidget()

        //show ปุ่ม back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //check ว่าปุ่มที่เลือก ได้กด ปุ่ม back หรือไม่
        if(item.itemId == android.R.id.home){
            finish() //จบหน้านั้นในตัว //กลับไปหน้าก่อนหน้า
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupWidget() {
        binding.buttonSubmit.setOnClickListener {
            var name = binding.edittextName.text.toString()
            var price = binding.edittextPrice.text.toString()
            val stock = binding.edittextStock.text.toString()

            //function
            val textToNumber: (String) -> Int = { text ->
                if (text.isEmpty()) {
                    0
                } else {
                    text.toInt()
                }

            }

            val data = ProductRequest(name, textToNumber(price), textToNumber(stock))
            addProduct(data)
        }
    }

    private fun addProduct(product: ProductRequest) {
        APIClient.getClient().create(APIService::class.java).addProduct(product).let { call ->
            Log.d("cm_network", call.request().toString())
            call.enqueue(object : Callback<Any> {
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    showToast(t.message.toString())
                }

                override fun onResponse(
                    call: Call<Any>,
                    response: Response<Any>
                ) {
                    if (response.isSuccessful) {
                        finish()

                    } else {
                        showToast("Add Product Failure!!")
                    }
                }

            })
        }
    }
}