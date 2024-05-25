package com.vinyl.app.activities

import com.vinyl.app.fragments.AlbumCreateFragment

//package com.vinyl.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyl.app.R

class AlbumCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_create)

        // Check if the fragment is already added
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AlbumCreateFragment())
                .commit()
        }
    }
}
