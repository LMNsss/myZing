package com.ngoclm.myzing.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ngoclm.myzing.R
import com.ngoclm.myzing.databinding.ActivityMainBinding
import com.ngoclm.myzing.ui.discovery.DiscoveryFragment
import com.ngoclm.myzing.ui.library.LibraryFragment
import com.ngoclm.myzing.ui.mainPlayFragment.MainFragment
import com.ngoclm.myzing.ui.profile.ProfileFragment
import com.ngoclm.myzing.ui.radio.RadioFragment
import com.ngoclm.myzing.ui.zingchart.ZingchartFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        events()

    }

    private fun events(){
        binding.bottomNavigation.selectedItemId = R.id.btn_discovery
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.btn_library -> replaceFragment(LibraryFragment())
                R.id.btn_discovery -> replaceFragment(DiscoveryFragment())
                R.id.btn_zing_chart -> replaceFragment(ZingchartFragment())
                R.id.btn_radio -> replaceFragment(RadioFragment())
                else -> {
                    replaceFragment(ProfileFragment())
                }
            }
            true
        }

        binding.layoutSong.setOnClickListener(){

        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, fragment)
        fragmentTransaction.commit()
    }
}

