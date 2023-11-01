package com.ngoclm.myzing.ui

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.ActivityMainBinding
import com.ngoclm.myzing.ui.discovery.DiscoveryFragment
import com.ngoclm.myzing.ui.library.LibraryFragment
import com.ngoclm.myzing.ui.playSong.PlaySongFragment
import com.ngoclm.myzing.ui.profile.ProfileFragment
import com.ngoclm.myzing.ui.radio.RadioFragment
import com.ngoclm.myzing.ui.zingchart.ZingchartFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var shareViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shareViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        events()
    }

    private fun events() {
        binding.bottomNavigation.selectedItemId = R.id.btn_discovery
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btn_library -> replaceFragment(LibraryFragment())
                R.id.btn_discovery -> replaceFragment(DiscoveryFragment())
                R.id.btn_zing_chart -> replaceFragment(ZingchartFragment())
                R.id.btn_radio -> replaceFragment(RadioFragment())
                else -> {
                    replaceFragment(ProfileFragment())
                }
            }
            true
        }

        binding.miniPlayerMusic.setOnClickListener() {
            val dialogBottomSheet = PlaySongFragment()
            dialogBottomSheet.show(supportFragmentManager, "bottom-sheet")
        }

        binding.icPlay.setOnClickListener() {
            val dialogBottomSheet = PlaySongFragment()
            dialogBottomSheet.show(supportFragmentManager, "bottom-sheet")
        }

        shareViewModel.selected.observe(this, Observer {
            binding.tvSongName.text = it.songName
            Glide.with(this).load(it.img).into(binding.imgSong)
            binding.tvSingerName.text = it.singerName
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, fragment)
        fragmentTransaction.commit()
    }


}






