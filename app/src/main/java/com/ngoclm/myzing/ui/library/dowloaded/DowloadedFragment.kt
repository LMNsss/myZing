package com.ngoclm.myzing.ui.library.dowloaded

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.FragmentDowloadedBinding


class DowloadedFragment : Fragment() {
   private lateinit var binding: FragmentDowloadedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDowloadedBinding.inflate(layoutInflater)
        return binding.root
    }



}