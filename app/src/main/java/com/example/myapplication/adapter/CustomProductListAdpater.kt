package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomProductListBinding
import com.example.myapplication.models.JsonDemoResultItem

class CustomProductListAdapter(var productList: List<JsonDemoResultItem>?) :
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

        //scope fun ช่วยไม่ต้องพิมพ์ binding
        with(binding) {

//            if(productList != null){
//
//            }
            //เหมือนตัวอย่างด้านบน  ถ้า productList เป็น null จะเอาค่าใน let{}
            productList?.let { list ->
                //มีค่าชัวร์
                val item = list[position]
                val lorem = item.name
                imageViewProduct.setImageResource(R.drawable.banner)
                textViewName.text = lorem
                textViewDetail.text = lorem
                textViewPrice.text = "฿ 25,000"
                textViewStock.text = "52220 price"
            }

        }
    }


    inner class ViewHolder(view: View, val binding: CustomProductListBinding) :
        RecyclerView.ViewHolder(view) {}
}