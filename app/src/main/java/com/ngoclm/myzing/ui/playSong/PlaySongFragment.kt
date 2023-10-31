package com.ngoclm.myzing.ui.playSong

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.FragmentPlaySongBinding
import com.ngoclm.myzing.ui.adapter.PlaySongTabLayoutAdapter

class PlaySongFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPlaySongBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaySongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = PlaySongTabLayoutAdapter(requireActivity())
        addEvents()
        viewPagerEvents()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }

        return dialog
    }

    private fun connectTablayout() {
        // Connect the TabLayout and the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Info"
                }

                1 -> {
                    tab.text = "Play"
                }

                2 -> {
                    tab.text = "Lyric"
                }
            }
        }.attach()
    }

    private fun viewPagerEvents() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.tvInfoFragment.text = getString(R.string.info)
                    Log.d("ngoc", "onPageSelected: 1")
                } else {
                    if (position == 1) {
                        Log.d("ngoc", "onPageSelected: 2")
                    } else {
                        binding.tvInfoFragment.text = getString(R.string.lyric)
                        Log.d("ngoc", "onPageSelected: 3")
                    }
                }

                super.onPageSelected(position)
            }
        })
    }

    private fun addEvents() {
        binding.btnDown.setOnClickListener {

        }

        binding.btnMenu.setOnClickListener {

        }
    }


    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet!!)
        val layoutParams = bottomSheet.layoutParams

        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.peekHeight = 0
    }

    private fun getWindowHeight(): Int { // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }


}