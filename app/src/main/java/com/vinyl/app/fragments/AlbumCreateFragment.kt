package com.vinyl.app.fragments
//package com.vinyl.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vinyl.app.R
import com.vinyl.app.pojo.Album
import com.vinyl.app.viewmodel.AlbumViewModel

class AlbumCreateFragment : Fragment() {

    private lateinit var albumViewModel: AlbumViewModel

    private lateinit var albumCoverInput: EditText
    private lateinit var albumDescriptionInput: EditText
    private lateinit var albumGenreInput: EditText
    private lateinit var albumNameInput: EditText
    private lateinit var albumRecordLabelInput: EditText
    private lateinit var albumReleaseDateInput: EditText
    private lateinit var createButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)

        albumCoverInput = view.findViewById(R.id.albumCoverInput)
        albumDescriptionInput = view.findViewById(R.id.albumDescriptionInput)
        albumGenreInput = view.findViewById(R.id.albumGenreInput)
        albumNameInput = view.findViewById(R.id.albumNameInput)
        albumRecordLabelInput = view.findViewById(R.id.albumRecordLabelInput)
        albumReleaseDateInput = view.findViewById(R.id.albumReleaseDateInput)
        createButton = view.findViewById(R.id.createButton)

        createButton.setOnClickListener {
            val cover = albumCoverInput.text.toString()
            val description = albumDescriptionInput.text.toString()
            val genre = albumGenreInput.text.toString()
            val name = albumNameInput.text.toString()
            val recordLabel = albumRecordLabelInput.text.toString()
            val releaseDate = albumReleaseDateInput.text.toString()

            if (cover.isNotEmpty() && description.isNotEmpty() && genre.isNotEmpty() &&
                name.isNotEmpty() && recordLabel.isNotEmpty() && releaseDate.isNotEmpty()) {
                val album = Album(
                    id = "",
                    cover = cover,
                    description = description,
                    genre = genre,
                    name = name,
                    recordLabel = recordLabel,
                    releaseDate = releaseDate
                )
                albumViewModel.createAlbum(album)
                Toast.makeText(requireContext(), "Album created successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

