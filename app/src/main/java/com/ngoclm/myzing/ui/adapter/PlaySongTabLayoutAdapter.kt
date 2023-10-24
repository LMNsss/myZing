package com.ngoclm.myzing.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ngoclm.myzing.ui.library.playlist_and_album.PlayListFragment
import com.ngoclm.myzing.ui.playSong.infoSong.InfoSongFragment
import com.ngoclm.myzing.ui.playSong.lyric.LyricFragment
import com.ngoclm.myzing.ui.playSong.playMusic.PlayMusicFragment

class PlaySongTabLayoutAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    private val items = 3

    override fun getItemCount(): Int {
        return items
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InfoSongFragment()
            1 -> PlayMusicFragment()
            2 -> LyricFragment()
            else -> PlayMusicFragment()
        }
    }
}