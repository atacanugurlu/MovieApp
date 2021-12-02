package com.example.movieapp

import TabAdapter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        tabLayout = binding.tabLayout
        viewPager = binding.tabsViewpager

        tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.main_red))
        tabLayout.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)

        val numberOfTabs = 2
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.isInlineLabel = true

        val adapter = TabAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = true


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Main Page"
                    tab.setIcon(R.drawable.icon_home)
                }
                1 -> {
                    tab.text = "Favourites"
                    tab.setIcon(R.drawable.icon_favourite_filled)

                }
            }
        }.attach()



    }
}

