package com.vinyl.app.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.vinyl.app.R
import com.vinyl.app.adapters.AlbumCatalogAdapter
import com.vinyl.app.adapters.MusicianListAdapter
import com.vinyl.app.databinding.FragmentHomeBinding
import com.vinyl.app.fragments.MusicianListFragment
import com.vinyl.app.pojo.Album
import com.vinyl.app.viewmodel.MusicianViewModel

class MusicianListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musician_list) // Set the content view to your activity layout

        // Initialize the MusicianListFragment
        val musicianListFragment = MusicianListFragment()

        // Begin a fragment transaction to replace the fragment container with the MusicianListFragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, musicianListFragment)
            commit()
        }
    }



}