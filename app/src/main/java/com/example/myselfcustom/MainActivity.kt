package com.example.myselfcustom

import android.os.Bundle
import androidx.navigation.createGraph
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.fragment
import com.example.myselfcustom.arch.livedatabus.EventCenterCore
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.ui.HomePageFragment
import com.example.myselfcustom.ui.OtherPageFragment
import com.example.myselfcustom.ui.slideleftfragment.SlideClickEvent

class MainActivity : BaseActivity<ActivityMainBinding>(), CommonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventCenterCore.of(SlideClickEvent::class.java).clickEvent().observe(this) {
            binding.drawerLayout.closeDrawers()
        }
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id)
        if (navHostFragment != null) {
            val navController = navHostFragment.findNavController()
            navController.graph = navController.createGraph(
                startDestination = "HomePage"
            ) {
                fragment<HomePageFragment>("HomePage") {
                    label = "HomePage"
                }

                fragment<OtherPageFragment>("OtherPage") {
                    label = "OtherPage"
                }
            }
        }
    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out Any>) {
    }

}