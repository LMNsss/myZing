package com.ngoclm.myzing.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.databinding.FragmentLibraryBinding
import com.ngoclm.myzing.ui.library.adapter.PlayListAndAlbumPagerAdapter

class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
    private val tabLayout = binding.tabLayout
    private val pager2 = binding.viewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlayListAndAlbumPagerAdapter(this)
        pager2.adapter = adapter
        TabLayoutMediator(tabLayout, pager2) {tab, pos ->
            when(pos){
                0 -> {tab.text = "Playlist"}
                1 -> {tab.text = "Album"}
            }
        }.attach()
    }
}