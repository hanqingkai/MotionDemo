package com.qingkai.motiondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.home_classroom_icon_layout.*
import kotlinx.android.synthetic.main.home_find_icon_layout.*
import kotlinx.android.synthetic.main.home_my_icon_layout.*
import kotlinx.android.synthetic.main.home_playground_icon_layout.*

/**
 * 不要在意fragment
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment)
        val destinationMap = mapOf(
            R.id.findFragment to findMotionLayout,
            R.id.classRommFragment to classroomMotionLayout,
            R.id.playgroundFragment to playgroundMotionLayout,
            R.id.myFragment to myMotionLayout
        )
        setupActionBarWithNavController(navController, AppBarConfiguration(destinationMap.keys))
        destinationMap.forEach { map -> map.value.setOnClickListener { navController.navigate(map.key) } }
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            //清空返回站，避免回弹
            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0f }
            destinationMap[destination.id]?.transitionToEnd()
        }
    }
}