package com.vinyl.app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vinyl.app.R
import com.vinyl.app.fragments.CollectorListFragment
import com.vinyl.app.fragments.MusicianListFragment

class CollectorListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collector_list) // Set the content view to your activity layout

        // Initialize the MusicianListFragment
        val collectorListFragment = CollectorListFragment()

        // Begin a fragment transaction to replace the fragment container with the MusicianListFragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, collectorListFragment)
            commit()
        }
    }
}