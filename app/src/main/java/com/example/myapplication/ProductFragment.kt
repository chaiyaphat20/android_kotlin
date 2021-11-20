package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.CustomProductListAdapter
import com.example.myapplication.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var customAdapter: CustomProductListAdapter;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product, container, false)

        binding = FragmentProductBinding.inflate(layoutInflater)
        customAdapter = CustomProductListAdapter(arrayListOf("bbb"))
        binding.productRecycleView.apply {
            adapter = customAdapter
            //LinearLayoutManager เป็นการบอกว่า Recycle view จะ scroll แบบ แนวตั้ง
            //layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false) //เลื่อนแนวนอน
            layoutManager = GridLayoutManager(context, 2)

//            layoutManager = LinearLayoutManager(context)

        }.also {
            it.addItemDecoration(GridSpacingItemDecoration(2, 30, true))
            it.setHasFixedSize(true)
        }
        return binding.root
    }

}