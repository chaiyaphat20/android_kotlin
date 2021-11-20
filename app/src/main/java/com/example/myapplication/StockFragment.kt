package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.CustomStockListAdapter
import com.example.myapplication.databinding.FragmentStockBinding

class StockFragment : Fragment() {
    private lateinit var binding: FragmentStockBinding
    private lateinit var customAdapter: CustomStockListAdapter;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product, container, false)

        binding = FragmentStockBinding.inflate(layoutInflater)

        customAdapter = CustomStockListAdapter(arrayListOf("aaa", "aaa", "aaa"))

        binding.stockRecycleView.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(context)

        }.also {
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            it.setHasFixedSize(true)  //เกี่ยวกับ performance ว่า พวก list จะมีขนาดคงที่ ช่วยทำให้ app ไม่ต้องมาคำนวณอีก
        }
        return binding.root
    }
}