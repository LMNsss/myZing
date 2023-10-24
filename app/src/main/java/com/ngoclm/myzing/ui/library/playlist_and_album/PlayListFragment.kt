package com.ngoclm.myzing.ui.library.playlist_and_album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngoclm.myzing.base.entities.Playlist
import com.ngoclm.myzing.base.interaction.onClickListener
import com.ngoclm.myzing.databinding.FragmentPlayListBinding
import com.ngoclm.myzing.ui.adapter.PlaylistAdapter

class PlayListFragment : Fragment() {
    private lateinit var binding: FragmentPlayListBinding
    private val myViewModel: PlayListViewModel by lazy {
        ViewModelProvider(
            this, PlayListViewModel.PlayListViewModelFactory(requireActivity().application)
        )[PlayListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControls()
        addPlayList()
    }

    private fun initControls() {
        val adapter = PlaylistAdapter(object : onClickListener {
            override fun onClickItem(view: View, pos: Int) {
                super.onClickItem(view, pos)
                Toast.makeText(context, "$pos", Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvPlayList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvPlayList.adapter = adapter
        myViewModel.getAllPlayList().observe(viewLifecycleOwner, Observer {
            adapter.setSong(it)
        })
    }

    fun addPlayList() {
        val playlistAdd = Playlist(
            id = 1,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "Vpop",
            "Nghe nhiều"
        )
        myViewModel.insertPlayList(playlistAdd)

        val playlistAdd1 = Playlist(
            id = 2,
            img = "https://photo-resize-zmp3.zmdcdn.me/w600_r1x1_jpeg/covers/0/0/0037fa44fb1ffa4a5ec148a4c14dccdf_1463244937.jpg",
            "abc",
            "Lê Minh Ngọc"
        )
        myViewModel.insertPlayList(playlistAdd1)


    }
}