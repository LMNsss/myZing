package com.ngoclm.myzing.ui

import android.app.Activity
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ngoclm.myzing.R
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.databinding.ActivityMainBinding
import com.ngoclm.myzing.ui.discovery.DiscoveryFragment
import com.ngoclm.myzing.ui.library.LibraryFragment
import com.ngoclm.myzing.ui.playSong.PlaySongFragment
import com.ngoclm.myzing.ui.profile.ProfileFragment
import com.ngoclm.myzing.ui.radio.RadioFragment
import com.ngoclm.myzing.ui.zingchart.ZingchartFragment
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val shareViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
            this, MainActivityViewModel.ShareViewModelFactory(this.application)
        )[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mediaPlayer = MediaPlayer()
        setContentView(binding.root)
        initControls()
        events()
    }

    private fun initControls() {
        shareViewModel.getLastSong().observe(this, Observer {
            if (it == null) {
                binding.miniPlayerMusic.visibility = View.INVISIBLE
            } else {
                binding.miniPlayerMusic.visibility = View.VISIBLE
                binding.tvSongName.text = it.songName
                Glide.with(this).load(it.img).into(binding.imgSong)
                binding.tvSingerName.text = it.singerName

                val filePath= it.filePath
                Log.d("ngoc", "file: $filePath")
                binding.icLove.setOnClickListener() {

                }
                binding.icPlay.setOnClickListener() {
//                    playMusic(filePath)
//                    if (mediaPlayer?.isPlaying == true) {
//                        pauseMusic()
//                        it.setBackgroundResource(R.drawable.ic_pause)
//                    } else {
//                        playMusic(filePath)
//                        it.setBackgroundResource(R.drawable.ic_play_pause)
//                    }

                }
            }

        })
    }

    private fun playMusic(url: String) {

            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()

    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
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


        binding.icNextSong.setOnClickListener() {

        }
        binding.icLove.setOnClickListener() {

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, fragment)
        fragmentTransaction.commit()
    }


}






