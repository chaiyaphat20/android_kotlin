package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.CustomProductListAdapter
import com.example.myapplication.databinding.FragmentProductBinding
import com.example.myapplication.models.ProductResponseItem
import com.example.myapplication.services.APIClient
import com.example.myapplication.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var customAdapter: CustomProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product, container, false)
        binding = FragmentProductBinding.inflate(layoutInflater)
        customAdapter = CustomProductListAdapter(null)
        setUpWidget()
        return binding.root
    }

    private fun feedNetWork() {
        binding.swipeRefresh.isRefreshing = true
        //retrofit2
        APIClient.getClient().create(APIService::class.java).getProducts().let { call ->
            Log.d("cm_network", call.request().toString())
            //Anonymous Object , object expression
            call.enqueue(object : Callback<List<ProductResponseItem>> {
                override fun onResponse(
                    call: Call<List<ProductResponseItem>>,
                    response: Response<List<ProductResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        binding.productRecycleView.adapter =
                            CustomProductListAdapter(response.body())
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

    private fun setUpWidget() {
        binding.productRecycleView.apply {
            adapter = customAdapter
            //LinearLayoutManager เป็นการบอกว่า Recycle view จะ scroll แบบ แนวตั้ง
            //layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false) //เลื่อนแนวนอน
            layoutManager = GridLayoutManager(context, 2)

        }.also {
            it.addItemDecoration(GridSpacingItemDecoration(2, 30, true))
            it.setHasFixedSize(true)
        }
        //ถ้า กดรูทลงจะ call feed network อีกครั้ง
        binding.swipeRefresh.setOnRefreshListener {
            feedNetWork()
        }
    }

    override fun onResume() {
        super.onResume()
        feedNetWork()
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.showToast("Exit")
    }

}