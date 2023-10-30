package com.ngoclm.myzing.base.interaction

import android.content.ClipData.Item
import android.view.View
import com.ngoclm.myzing.base.entities.Song

interface onClickListener {
    fun onClickItem(song: Song){}
}