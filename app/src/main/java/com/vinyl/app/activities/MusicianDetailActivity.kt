package com.vinyl.app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.vinyl.app.R
import com.vinyl.app.databinding.ActivityMusicianDetailBinding
import com.vinyl.app.fragments.MusicianDetailFragment
import com.vinyl.app.fragments.MusicianListFragment

class MusicianDetailActivity : AppCompatActivity() {

    private lateinit var musicianId : String
    private lateinit var musicianName : String
    private lateinit var musicianBirthDate : String
    private lateinit var musicianDescription : String
    private lateinit var musicianImage : String
    private lateinit var binding : ActivityMusicianDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicianDetailBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the content view to your activity layout

        getMusicianInfoFromIntent()
        setInfoInViews()
    }

    private fun setInfoInViews() {
        Glide.with(applicationContext)
            .load(musicianImage)
            .into(binding.artistDetailImg)

        binding.artistNameTv.text = musicianName
        binding.artistBirthdateTv.text = musicianBirthDate
        binding.artistDescriptionTv.text = musicianDescription
    }

    private fun getMusicianInfoFromIntent() {
        val intent = intent
        musicianId = intent.getStringExtra(MusicianListFragment.MUSICIAN_ID)!!
        musicianName = intent.getStringExtra(MusicianListFragment.MUSICIAN_NAME)!!
        musicianDescription = intent.getStringExtra(MusicianListFragment.MUSICIAN_DESCRIPTION)!!
        musicianBirthDate = intent.getStringExtra(MusicianListFragment.MUSICIAN_BIRTHDATE)!!
        musicianImage = intent.getStringExtra(MusicianListFragment.MUSICIAN_IMAGE)!!
    }
}