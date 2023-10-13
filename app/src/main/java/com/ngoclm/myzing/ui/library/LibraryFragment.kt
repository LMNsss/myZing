package com.ngoclm.myzing.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.R
import com.ngoclm.myzing.base.db.SongDatabase
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.interaction.onClickListener
import com.ngoclm.myzing.databinding.FragmentLibraryBinding
import com.ngoclm.myzing.ui.library.adapter.PlayListAndAlbumPagerAdapter
import com.ngoclm.myzing.ui.library.adapter.RecentlyListAdapter
import com.ngoclm.myzing.ui.library.playlist_and_album.AlbumFragment
import com.ngoclm.myzing.ui.library.playlist_and_album.PlayListFragment

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
        addSong()
        initEvents()
        initControls()

    }

    private fun initEvents() {
        TODO("Not yet implemented")
    }

    private fun initControls() {
        val adapter = RecentlyListAdapter(object : onClickListener {
            override fun onClickItem(view: View, pos: Int) {
                super.onClickItem(view, pos)
            }
        })
        binding.rvListenRecently.layoutManager = LinearLayoutManager(context)
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
    }
    private fun addSong() {
        val songAddNew = Song(
            1,
            "http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg",
            "em có yêu anh không",
            "Only c"
        )
        myViewModel.insertSong(songAddNew)
    }
}