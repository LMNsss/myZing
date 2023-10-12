package com.ngoclm.myzing.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.databinding.FragmentLibraryBinding
import com.ngoclm.myzing.ui.library.adapter.PlayListAndAlbumPagerAdapter
import com.ngoclm.myzing.ui.library.adapter.RecentlyListAdapter
import com.ngoclm.myzing.ui.library.playlist_and_album.AlbumFragment
import com.ngoclm.myzing.ui.library.playlist_and_album.PlayListFragment

class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the ViewPager2 with the adapter
        binding.viewPager.adapter = PlayListAndAlbumPagerAdapter(requireActivity())

        // Connect the TabLayout and the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Playlist"
                    binding.icMenuPlaylist.visibility = View.VISIBLE
                }

                1 -> {
                    tab.text = "Album"
                    binding.icMenuPlaylist.visibility = View.INVISIBLE
                }
            }
        }.attach()
    }
}