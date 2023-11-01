package com.ngoclm.myzing.ui.playSong.infoSong

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.FragmentInfoSongBinding
import com.ngoclm.myzing.databinding.FragmentInfoSongBindingImpl
import com.ngoclm.myzing.ui.MainActivityViewModel


class InfoSongFragment : Fragment() {
    private lateinit var binding: FragmentInfoSongBinding
    private lateinit var shareViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoSongBinding.inflate(layoutInflater)
        shareViewModel = activity?.run {
            ViewModelProvider(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        addEvent()
        return binding.root
    }

    private fun addEvent(){
        shareViewModel.selected.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.img).into(binding.imgSong)
            binding.tvSongName.text = it.songName
            binding.tvSingerName.text = it.singerName
            binding.tvDetailArtist.text = it.singerName
            binding.tvLikeNumber.text = it.loveNumber.toString()
            binding.tvListenNumber.text = it.listensNumber.toString()
        })
    }

}