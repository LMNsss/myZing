package com.ngoclm.myzing.ui.library.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ngoclm.myzing.ui.library.LibraryFragment
import com.ngoclm.myzing.ui.library.playlist_and_album.AlbumFragment
import com.ngoclm.myzing.ui.library.playlist_and_album.PlayListFragment

class PlayListAndAlbumPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PlayListFragment()
            }
            else -> {
                AlbumFragment()
            }
        }
    }
}