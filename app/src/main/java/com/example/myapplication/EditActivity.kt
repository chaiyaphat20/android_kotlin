package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityEditBinding
import com.example.myapplication.models.ProductRequest
import com.example.myapplication.models.ProductResponseItem
import com.example.myapplication.services.APIClient
import com.example.myapplication.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {
    private var product: ProductResponseItem? = null
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data Intent
        product = intent.getParcelableExtra(INTENT_PRODUCT)
        product?.let {
            showToast(product.toString())
        }

        setupWidget()

        //show ปุ่ม back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //check ว่าปุ่มที่เลือก ได้กด ปุ่ม back หรือไม่
        if (item.itemId == android.R.id.home) {
            finish() //จบหน้านั้นในตัว //กลับไปหน้าก่อนหน้า
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupWidget() {
        product?.let { productList ->
            //bing data show in update stock
            binding.edittextName.setText(productList.name)
            binding.edittextPrice.setText(productList.price.toString())
            binding.edittextStock.setText(productList.stock.toString())

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
                Log.d("data22222",productList.id.toString())
                editProduct(productList.id, data)
            }

            //delete product
            binding.buttonDelete.setOnClickListener {
                deleteProduct(productList.id)
            }
        }

    }

    private fun editProduct(id: Int, product: ProductRequest) {
        APIClient.getClient().create(APIService::class.java).editProduct(id, product).let { call ->
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
                        showToast("Edit Product Failure!!")
                    }
                }

            })
        }
    }

    private fun deleteProduct(id: Int) {
        APIClient.getClient().create(APIService::class.java).deleteProduct(id).let { call ->
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
                        showToast("Delete Product Failure!!")
                    }
                }

            })
        }
    }
}