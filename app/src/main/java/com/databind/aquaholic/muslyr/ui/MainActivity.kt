package com.databind.aquaholic.muslyr.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.databind.aquaholic.muslyr.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    /*
        private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_history -> {
                    message.setText(R.string.title_history)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    message.setText(R.string.title_search)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_featured -> {
                    message.setText(R.string.title_featured)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    */
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
