package com.example.myapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.R

class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    lifeCycle: Lifecycle
) :
    FragmentStateAdapter(fm, lifeCycle) {

    val tabIcon: Array<Int> = arrayOf(R.drawable.ic_product, R.drawable.ic_stock)

    //มันจะได้ {"product" , stock} ex.**จะได้ arrayOf({"product" , stock})
    //จริงๆ เราอยากได้ "product" , "stock"
    //เราเลยใช้ * ซึ่ง คล้ายกับ spread operation ของ JS
    //จะได้ arrayOf("product" , "stock")
    val tabText: Array<String> = arrayOf(*context.resources.getStringArray(R.array.tab_title))

    override fun getItemCount() = tabIcon.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ProductFragment()
            }
            else -> {
                StockFragment()
            }
        }

    }

}
