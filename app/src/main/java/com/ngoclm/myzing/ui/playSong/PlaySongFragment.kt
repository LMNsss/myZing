package com.ngoclm.myzing.ui.playSong

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.FragmentPlaySongBinding
import com.ngoclm.myzing.ui.adapter.PlaySongTabLayoutAdapter

class PlaySongFragment : Fragment() {
    private lateinit var binding: FragmentPlaySongBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaySongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = PlaySongTabLayoutAdapter(requireActivity())

        // Connect the TabLayout and the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Info"
                }

                1 -> {
                    tab.text = "Play"
                }

                2 -> {
                    tab.text = "Lyric"
                }
            }
        }.attach()


        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.tvInfoFragment.text = getString(R.string.info)
                    Log.d("ngoc", "onPageSelected: 1")
                } else {
                    if (position == 1) {
                        Log.d("ngoc", "onPageSelected: 2")
                    } else {
                        binding.tvInfoFragment.text = getString(R.string.lyric)
                        Log.d("ngoc", "onPageSelected: 3")
                    }
                }

                super.onPageSelected(position)
            }
        })
    }

    fun addEvents(){
        binding.btnDown.setOnClickListener(){
            val transaction
        }
    }



}