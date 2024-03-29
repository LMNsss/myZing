package com.ngoclm.myzing.ui


import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false
    private var startApp = false
    private lateinit var selectSong: Song
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

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }

    private fun initControls() {
        shareViewModel.startApp.observe(this, Observer {
            startApp = it
            Log.d("startApp", it.toString())
        })

        shareViewModel.isPlaying.observe(this, Observer {
            isPlaying = it
            if (isPlaying) {
                binding.icPlay.setImageResource(R.drawable.ic_pause)
                mediaPlayer.start()
            } else {
                binding.icPlay.setImageResource(R.drawable.ic_play_pause)
                mediaPlayer.pause()
            }
        })

        shareViewModel.selectedSong.observe(this, Observer {
            if (it == null) {
                binding.miniPlayerMusic.visibility = View.INVISIBLE
            } else {
                selectSong = it
                binding.miniPlayerMusic.visibility = View.VISIBLE
                binding.tvSongName.text = it.songName
                Glide.with(this).load(it.img).into(binding.imgSong)
                binding.tvSingerName.text = it.singerName
                if (it.love) binding.icLove.setImageResource(R.drawable.ic_heart1)
                else binding.icLove.setImageResource(R.drawable.ic_heart)
                if (!startApp) {
                    playMusic(it.filePath)
//                    binding.icPlay.setImageResource(R.drawable.ic_pause)
                    shareViewModel.secondPlay()
                    shareViewModel.setPlaying(true)
                }
            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun events() {
        binding.bottomNavigation.selectedItemId = R.id.btn_discovery
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btn_library -> {
                    replaceFragment(LibraryFragment())
                    binding.tvTitle.text = "Thư viện"
                }

                R.id.btn_discovery -> {
                    replaceFragment(DiscoveryFragment())
                    binding.tvTitle.text = "Khám phá"
                }

                R.id.btn_zing_chart -> {
                    replaceFragment(ZingchartFragment())
                    binding.tvTitle.text = "#ZingChart"
                }

                R.id.btn_radio -> {
                    replaceFragment(RadioFragment())
                    binding.tvTitle.text = "Radio"
                }

                else -> {
                    replaceFragment(ProfileFragment())
                    binding.tvTitle.text = "Cá nhân"
                }
            }
            true
        }

        binding.miniPlayerMusic.setOnClickListener() {
            val dialogBottomSheet = PlaySongFragment()
            dialogBottomSheet.show(supportFragmentManager, "bottom-sheet")
        }

        binding.icLove.setOnClickListener() {
            if (selectSong.love) {
                selectSong.love = false
                binding.icLove.setImageResource(R.drawable.ic_heart)
                shareViewModel.updateSong(selectSong)
                Toast.makeText(
                    this,
                    "Đã bỏ thích bài hát ${selectSong.songName}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                selectSong.love = true
                binding.icLove.setImageResource(R.drawable.ic_heart1)
                shareViewModel.updateSong(selectSong)
                Toast.makeText(this, "Đã thích bài hát ${selectSong.songName}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        shareViewModel.firstPlay.observe(this, Observer {
            val firstPlay = it
            binding.icPlay.setOnClickListener() {
                if (firstPlay == true) {
                    playMusic(selectSong.filePath)
                    shareViewModel.secondPlay()
                    shareViewModel.setPlaying(true)
                } else {
                    if (mediaPlayer.isPlaying) {
                        pauseMusic()
                        shareViewModel.setPlaying(false)
                    } else {
                        mediaPlayer.start()
                        shareViewModel.setPlaying(true)
                    }
                }
            }
        })

        binding.icNextSong.setOnClickListener() {
        }


    }

    private fun playMusic(url: String) {
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    private fun stopMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, fragment)
        fragmentTransaction.commit()
    }


}






