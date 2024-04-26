package com.vinyl.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArtistDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artist_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre")
        val nacionalidad = intent.getStringExtra("nacionalidad")
        val biografia = intent.getStringExtra("biografia")

        val getNameTextView : TextView = findViewById(R.id.artist_name_text_view)
        val getNationalityTextView : TextView = findViewById(R.id.artist_nationality_text_view)
        val getBiographyTextView : TextView = findViewById(R.id.artist_biography_text_view)

        getNameTextView.text = nombre
        getNationalityTextView.text = nacionalidad
        getBiographyTextView.text = biografia

        val previousButton : Button = findViewById(R.id.button_second)

        previousButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}