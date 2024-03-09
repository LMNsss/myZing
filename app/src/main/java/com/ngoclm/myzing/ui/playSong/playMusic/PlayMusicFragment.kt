package com.ngoclm.myzing.ui.playSong.playMusic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.FragmentPlayMusicBinding
import com.ngoclm.myzing.ui.MainActivityViewModel


class PlayMusicFragment : Fragment() {
    private lateinit var binding: FragmentPlayMusicBinding
    private val shareViewModel : MainActivityViewModel by activityViewModels()
    private var isPlaying: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayMusicBinding.inflate(layoutInflater)
        initControls()
        addEvent()
        return binding.root
    }

    fun initControls(){
        shareViewModel.isPlaying.observe(viewLifecycleOwner, Observer {
            isPlaying = it
            if (isPlaying){
                binding.btnPause.setBackgroundResource(R.drawable.ic_pause_circle)
            }
            else{
                binding.btnPause.setBackgroundResource(R.drawable.ic_play_cicle)
            }
        })
    }
    @SuppressLint("ResourceType")
    private fun addEvent() {
        shareViewModel.selectedSong.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(this).load(it.img).into(binding.imgSong)
                binding.tvSongName.text = it.songName
                binding.tvSingerName.text = it.singerName
            }
        })

        binding.btnPause.setOnClickListener{
            if(isPlaying){
                binding.btnPause.setBackgroundResource(R.drawable.ic_pause_circle)
                shareViewModel.setPlaying(false)
            }
            else{
                binding.btnPause.setBackgroundResource(R.drawable.ic_play_cicle)
                shareViewModel.setPlaying(true)
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser){

                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }
}