package com.ngoclm.myzing.base.interaction

import android.view.View

interface onClickListener {
    fun onClickButton(view: View, pos: Int){}
    fun onClickItem(view: View, pos: Int){}
}