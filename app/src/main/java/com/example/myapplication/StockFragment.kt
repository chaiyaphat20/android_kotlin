package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.CustomStockListAdapter
import com.example.myapplication.databinding.FragmentStockBinding
import com.example.myapplication.models.ProductResponseItem
import com.example.myapplication.services.APIClient
import com.example.myapplication.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockFragment : Fragment() {
    private lateinit var binding: FragmentStockBinding
    private lateinit var customAdapter: CustomStockListAdapter;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStockBinding.inflate(layoutInflater)
        customAdapter = CustomStockListAdapter(null)

        setUpWidget()

        return binding.root
    }

    private fun setUpWidget() {
        binding.stockRecycleView.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(context)

        }.also {
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            it.setHasFixedSize(true)  //เกี่ยวกับ performance ว่า พวก list จะมีขนาดคงที่ ช่วยทำให้ app ไม่ต้องมาคำนวณอีก
        }
        binding.swipeRefresh.setOnRefreshListener {
            feedNetWork()
        }
    }

    private fun feedNetWork() {
        binding.swipeRefresh.isRefreshing = true
        //retrofit2
        val service =
            APIClient.getClient().create(APIService::class.java).getProducts().let { call ->
                Log.d("cm_network", call.request().toString())
                //Anonymous Object , object expression
                call.enqueue(object : Callback<List<ProductResponseItem>> {
                    override fun onResponse(
                        call: Call<List<ProductResponseItem>>,
                        response: Response<List<ProductResponseItem>>
                    ) {
                        if (response.isSuccessful) {
                            binding.stockRecycleView.adapter =
                                CustomStockListAdapter(response.body())
                        } else {
                            context?.showToast(response.message())
                        }
                        binding.swipeRefresh.isRefreshing = false

                    }

                    override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                        context?.showToast(t.message.toString())
                        binding.swipeRefresh.isRefreshing = false
                    }

                })
            }
    }

    //เข้ามาหน้าแรก ทำงานหลัง onCreate และ Started //หลงกลับมา
    override fun onResume() {
        super.onResume()
        feedNetWork()
    }
}