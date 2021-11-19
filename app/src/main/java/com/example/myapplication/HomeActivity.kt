package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.CustomTabMenuBinding
import com.example.myapplication.ui.main.SectionsPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, lifecycle)

        setupViewPager()
        setUpWidget()
        setUpTab()


    }

    private fun setUpTab() {
        TabLayoutMediator(binding.tabs, binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val binding = CustomTabMenuBinding.inflate(layoutInflater)
                //sectionsPagerAdapter เข้าไปที่ SectionPagerAdapter
                binding.iconTab.setImageResource(sectionsPagerAdapter.tabIcon[position])
                binding.textTab.text = (sectionsPagerAdapter.tabText[position])

                tab.customView = binding.root
            }).attach()
    }

    private fun setUpWidget() {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //set Page
    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = sectionsPagerAdapter
            //Set Animation
            setPageTransformer(HorizontalFlipTransformation())
        }.also { xx ->
            //ใช้ it แทน
            xx.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    if (position == 0) {
                        //TODO
                        binding.fab.visibility = View.INVISIBLE
                    } else {
                        binding.fab.visibility = View.VISIBLE
                    }
                }
            });
        }
    }
}