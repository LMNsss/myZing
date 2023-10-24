package com.ngoclm.myzing.ui.library

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.interaction.onClickListener
import com.ngoclm.myzing.databinding.FragmentLibraryBinding
import com.ngoclm.myzing.ui.adapter.PlayListAndAlbumPagerAdapter
import com.ngoclm.myzing.ui.adapter.RecentlyListAdapter


class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
    private val myViewModel: LibraryViewModel by lazy {
        ViewModelProvider(
            this, LibraryViewModel.SongViewModelFactory(requireActivity().application)
        ).get(LibraryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        return binding.root


    }


    private fun initControls() {
        val adapter = RecentlyListAdapter(object : onClickListener {
            override fun onClickItem(view: View, pos: Int) {
                super.onClickItem(view, pos)
                Toast.makeText(context, "$pos", Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvListenRecently.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListenRecently.adapter = adapter
        myViewModel.getAllSong().observe(viewLifecycleOwner, Observer {
            adapter.setSong(it)
        })
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
                }

                1 -> {
                    tab.text = "Album"
                }
            }
        }.attach()


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.icMenuPlaylist.visibility = View.VISIBLE
                    Log.d("hieu", "onPageSelected: 1")
                } else if (position == 1) {
                    binding.icMenuPlaylist.visibility = View.INVISIBLE
                    Log.d("hieu", "onPageSelected: 2")
                }

                super.onPageSelected(position)
            }
        })

        addSong()
        initControls()

    }

    private fun addSong() {
        val songAddNew1 = Song(
            id = 1,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không",
            "Only c",
            true,
            true,
            "Vpop",
            true

        )
        myViewModel.insertSong(songAddNew1)

        val songAddNew2 = Song(
            id = 2,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 1",
            "Only c",
            true,
            true,
            "Vpop",
            true

        )
        myViewModel.insertSong(songAddNew2)

        val songAddNew3 = Song(
            id = 3,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 2",
            "Only c 1",
            true,
            false,
            "Best",
            true

        )
        myViewModel.insertSong(songAddNew3)

        val songAddNew4 = Song(
            id = 4,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 3",
            "Only c",
            true,
            true,
            "Best",
            true

        )
        myViewModel.insertSong(songAddNew4)

        val songAddNew5 = Song(
            id = 5,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 4",
            "Only c",
            true,
            false,
            "Vpop",
            true

        )
        myViewModel.insertSong(songAddNew5)

        val songAddNew6 = Song(
            id = 6,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 5",
            "Only c",
            true,
            false,
            "Vpop",
            true

        )
        myViewModel.insertSong(songAddNew6)
        val songAddNew7 = Song(
            id = 7,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 6",
            "Only c",
            true,
            true,
            "Vpop",
            true

        )
        myViewModel.insertSong(songAddNew7)
    }
}
