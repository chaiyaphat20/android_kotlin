package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomProductListBinding

class CustomProductListAdapter(var productList: ArrayList<String>?) :
    RecyclerView.Adapter<CustomProductListAdapter.ViewHolder>() {

    //Layout ในการแสดงผล
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomProductListAdapter.ViewHolder {
        //binding แบบ Adapter  **ต่างจากอันอื่น  รูท คือ parent
        val binding =
            CustomProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    //จำนวนการ แสดงผล
    override fun getItemCount() = productList?.size ?: 0

    //ตัว Context
    override fun onBindViewHolder(holder: CustomProductListAdapter.ViewHolder, position: Int) {
        val binding = holder.binding

        val lorem =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        binding.imageViewProduct.setImageResource(R.drawable.banner)
        binding.textViewName.text = lorem
        binding.textViewDetail.text = lorem
        binding.textViewPrice.text = "฿ 25,000"
        binding.textViewStock.text = "52220 price"

    }


    inner class ViewHolder(view: View, val binding: CustomProductListBinding) :
        RecyclerView.ViewHolder(view) {}
}