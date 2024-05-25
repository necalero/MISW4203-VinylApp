package com.vinyl.app.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vinyl.app.R
import com.vinyl.app.databinding.ActivityMainBinding
import com.vinyl.app.viewmodel.HomeViewModel
import com.vinyl.app.viewmodel.HomeViewModelFactory
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.getAlbums()
            swipeRefreshLayout.isRefreshing = false
            Toast.makeText(this, "Page Refreshed!", Toast.LENGTH_SHORT).show()
        }
    }
}

