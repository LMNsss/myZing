package com.ngoclm.myzing.ui.library

import android.media.MediaPlayer
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
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.R
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.interaction.onClickListener
import com.ngoclm.myzing.databinding.FragmentLibraryBinding
import com.ngoclm.myzing.ui.MainActivityViewModel
import com.ngoclm.myzing.ui.adapter.PlayListAndAlbumPagerAdapter
import com.ngoclm.myzing.ui.adapter.RecentlyListAdapter
import com.ngoclm.myzing.ui.library.dowloaded.DowloadedFragment
import kotlin.math.log


class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var lastSong: Song? = null
    private val myViewModel: LibraryViewModel by lazy {
        ViewModelProvider(
            this, LibraryViewModel.SongViewModelFactory(requireActivity().application)
        )[LibraryViewModel::class.java]
    }
    private val shareViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
            requireActivity().viewModelStore, MainActivityViewModel.ShareViewModelFactory(requireActivity().application)
        )[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
//        addSong()
        initControls()
        tablayout()
        mediaPlayer = MediaPlayer()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpagerCallBack()
        events()

    }

    private fun initControls() {
        shareViewModel.getLastSong().observe(viewLifecycleOwner, Observer {
            lastSong = it
        })
        val adapter = RecentlyListAdapter(object : onClickListener {
            override fun onClickItem(song: Song) {
                super.onClickItem(song)
                shareViewModel.setStartApp(false)
                shareViewModel.setSelectedSong(song)
                if (!song.recently) {
                    song.recently = true
                }
                if (lastSong != null && song.id != lastSong!!.id) {
                    song.last = true
                    lastSong!!.last = false
                    myViewModel.updateSong(lastSong!!)
                } else {
                    song.last = true
                }
                song.listensNumber += 1
                myViewModel.updateSong(song)
                Toast.makeText(context, "Đang phát ${song.songName}", Toast.LENGTH_SHORT).show()
            }

        })
        binding.rvListenRecently.setHasFixedSize(true)
        binding.rvListenRecently.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListenRecently.adapter = adapter

//        myViewModel.loadLocalSongs().observe(viewLifecycleOwner, Observer {
//            adapter.setSong(it)
//        })

        myViewModel.getSongByRecently(true).observe(viewLifecycleOwner, Observer {
            adapter.setSong(it)
        })

    }

    fun events(){
        binding.itemDowloaded.setOnClickListener(){
            replaceFragment(DowloadedFragment())

        }

    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.layout_container, fragment)
        fragmentTransaction.commit()
    }

    private fun viewpagerCallBack() {
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
    }

    private fun tablayout() {
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
        val songAddNew1 = Song(
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không",
            "Only c",
            true,
            true,
            "Vpop",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false
        )
        myViewModel.insertSong(songAddNew1)

        val songAddNew2 = Song(
            img = "https://designs.vn/wp-content/images/21-09-2017/lieu-co-phai-chup-anh-duoi-ca-hai-dinh-dang-jpeg-va-raw-la-ban-mot-mui-ten-trung-hai-dich-02.jpg",
            "em có yêu anh không 1",
            "Only c",
            true,
            true,
            "Vpop",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false

        )
        myViewModel.insertSong(songAddNew2)
//
        val songAddNew3 = Song(
            img = "https://vcdn-dulich.vnecdn.net/2021/12/24/An-Giang-0-jpeg-1470-1640315739.jpg",
            "em có yêu anh không 2",
            "Only c 1",
            true,
            false,
            "Best",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false

        )
        myViewModel.insertSong(songAddNew3)

        val songAddNew4 = Song(
            img = "https://cdn.vjshop.vn/tin-tuc/phan-biet-raw-va-jpeg/phan-biet-raw-va-jpeg-12.jpg",
            "em có yêu anh không 3",
            "Only c",
            true,
            true,
            "Best",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false

        )
        myViewModel.insertSong(songAddNew4)

        val songAddNew5 = Song(
            img = "https://vnreview.vn/image/16/81/51/1681516.jpg",
            "em có yêu anh không 4",
            "Only c",
            true,
            false,
            "Vpop",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false
        )
        myViewModel.insertSong(songAddNew5)

        val songAddNew6 = Song(
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 5",
            "Only c",
            true,
            false,
            "Vpop",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false

        )
        myViewModel.insertSong(songAddNew6)
        val songAddNew7 = Song(
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "em có yêu anh không 6",
            "Only c",
            true,
            true,
            "Vpop",
            true,
            "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            200,
            0,
            false
        )
        myViewModel.insertSong(songAddNew7)
    }
}
