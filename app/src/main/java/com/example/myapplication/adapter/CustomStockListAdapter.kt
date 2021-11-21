package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomStockListBinding
import com.example.myapplication.models.ProductResponseItem
import com.example.myapplication.services.APIClient

class CustomStockListAdapter(var stockList: List<ProductResponseItem>?) :
    RecyclerView.Adapter<CustomStockListAdapter.ViewHolder>() {

    //Layout ในการแสดงผล
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomStockListAdapter.ViewHolder {
        //binding แบบ Adapter  **ต่างจากอันอื่น  รูท คือ parent
        val binding =
            CustomStockListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    //จำนวนการ แสดงผล
    override fun getItemCount() = stockList?.size ?: 0

    //ตัว Context
    override fun onBindViewHolder(holder: CustomStockListAdapter.ViewHolder, position: Int) {
        val binding = holder.binding

        //scope fun ช่วยไม่ต้องพิมพ์ binding
        with(binding) {
            stockList?.let { list ->
                //มีค่าชัวร์
                val item = list[position]
                textViewName.text = item.name
                textViewDetail.text =
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy"
                textViewPrice.text = "฿ ${item.price}"
                textViewStock.text = "${item.stock} price"
                Glide.with(imageViewProduct.context)
                    .load(APIClient.getImageUrl() + item.image)
                    .error(R.drawable.logo)
                    .into(imageViewProduct)
            }
        }

    }


    inner class ViewHolder(view: View, val binding: CustomStockListBinding) :
        RecyclerView.ViewHolder(view) {}
}