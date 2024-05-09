package com.vinyl.app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.vinyl.app.R
import com.vinyl.app.databinding.ActivityCollectorDetailBinding
import com.vinyl.app.fragments.CollectorListFragment

class CollectorDetailActivity : AppCompatActivity() {
    private lateinit var collectorId : String
    private lateinit var collectorName : String
    private lateinit var collectorEmail : String
    private lateinit var collectorPhone : String
    private lateinit var binding : ActivityCollectorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the content view to your activity layout

        getCollectorInfoFromIntent()
        setInfoInViews()
    }

    private fun setInfoInViews() {

        binding.collectorNameTv.text = collectorName
        binding.collectorEmailTv.text = collectorEmail
        binding.collectorPhoneTv.text = collectorPhone
    }

    private fun getCollectorInfoFromIntent() {
        val intent = intent
        collectorName = intent.getStringExtra(CollectorListFragment.COLLECTOR_NAME)!!
        collectorEmail = intent.getStringExtra(CollectorListFragment.COLLECTOR_EMAIL)!!
        collectorPhone = intent.getStringExtra(CollectorListFragment.COLLECTOR_PHONE)!!
    }


}