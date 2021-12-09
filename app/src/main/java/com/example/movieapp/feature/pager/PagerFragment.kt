package com.example.movieapp.feature.pager

import TabAdapter
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.databinding.PagerFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PagerFragment : Fragment() {

    private lateinit var viewModel: PagerViewModel
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PagerFragmentBinding.inflate(inflater)
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
        tabLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_red))
        tabLayout.tabTextColors = ContextCompat.getColorStateList(requireContext(), R.color.white)

        val numberOfTabs = 2
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.isInlineLabel = true

        val adapter = TabAdapter(childFragmentManager, lifecycle, numberOfTabs)
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

        return binding.root
    }

}