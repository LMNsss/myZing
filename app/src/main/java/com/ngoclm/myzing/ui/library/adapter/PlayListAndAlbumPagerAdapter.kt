package com.ngoclm.myzing.ui.library.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ngoclm.myzing.ui.library.LibraryFragment
import com.ngoclm.myzing.ui.library.playlist_and_album.AlbumFragment
import com.ngoclm.myzing.ui.library.playlist_and_album.PlayListFragment

class PlayListAndAlbumPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    private val items = 2

    override fun getItemCount(): Int {
        return items
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlayListFragment()
            1 -> AlbumFragment()
            else -> PlayListFragment()
        }
    }
}