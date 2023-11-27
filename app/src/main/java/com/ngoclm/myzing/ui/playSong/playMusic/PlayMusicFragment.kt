package com.ngoclm.myzing.ui.playSong.playMusic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.FragmentPlayMusicBinding
import com.ngoclm.myzing.databinding.FragmentPlaySongBinding
import com.ngoclm.myzing.ui.MainActivityViewModel


class PlayMusicFragment : Fragment() {
    private lateinit var binding: FragmentPlayMusicBinding
    private lateinit var shareViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayMusicBinding.inflate(layoutInflater)
        shareViewModel = activity?.run {
            ViewModelProvider(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        addEvent()
        return binding.root
    }

    private fun addEvent() {
//        shareViewModel.getLastSong().observe(viewLifecycleOwner, Observer {
//            Glide.with(this).load(it.img).into(binding.imgSong)
//            binding.tvSongName.text = it.songName
//            binding.tvSingerName.text = it.singerName
//        })
    }


}