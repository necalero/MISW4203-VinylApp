package com.vinyl.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyl.app.R
import com.vinyl.app.fragments.AlbumDetailFragment

class AlbumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        val albumId = intent.getStringExtra("albumId")

        if (savedInstanceState == null && albumId != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AlbumDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString("albumId", albumId)
                    }
                })
                .commit()
        }
    }
}
