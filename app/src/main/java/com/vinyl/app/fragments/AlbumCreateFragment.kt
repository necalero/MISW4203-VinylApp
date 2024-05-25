package com.vinyl.app.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vinyl.app.R
import com.vinyl.app.pojo.Album
import com.vinyl.app.viewmodel.AlbumViewModel
import java.text.SimpleDateFormat
import java.util.*

class AlbumCreateFragment : Fragment() {

    private lateinit var albumViewModel: AlbumViewModel

    private lateinit var albumCoverInput: EditText
    private lateinit var albumDescriptionInput: EditText
    private lateinit var albumGenreSpinner: Spinner
    private lateinit var albumNameInput: EditText
    private lateinit var albumRecordLabelSpinner: Spinner
    private lateinit var albumReleaseDateInput: EditText
    private lateinit var createButton: Button

    private val calendar: Calendar = Calendar.getInstance()

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
        albumGenreSpinner = view.findViewById(R.id.albumGenreSpinner)
        albumNameInput = view.findViewById(R.id.albumNameInput)
        albumRecordLabelSpinner = view.findViewById(R.id.albumRecordLabelSpinner)
        albumReleaseDateInput = view.findViewById(R.id.albumReleaseDateInput)
        createButton = view.findViewById(R.id.createButton)

        // Configurar el Spinner con los géneros
        val genres = listOf("Classical", "Salsa", "Rock", "Folk")
        val genreAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        albumGenreSpinner.adapter = genreAdapter

        // Configurar el Spinner con los record labels
        val recordLabels = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
        val recordLabelAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, recordLabels)
        recordLabelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        albumRecordLabelSpinner.adapter = recordLabelAdapter

        createButton.setOnClickListener {
            val cover = albumCoverInput.text.toString()
            val description = albumDescriptionInput.text.toString()
            val genre = albumGenreSpinner.selectedItem.toString()
            val name = albumNameInput.text.toString()
            val recordLabel = albumRecordLabelSpinner.selectedItem.toString()
            val releaseDateStr = albumReleaseDateInput.text.toString()

            if (cover.isNotEmpty() && description.isNotEmpty() && genre.isNotEmpty() &&
                name.isNotEmpty() && recordLabel.isNotEmpty() && releaseDateStr.isNotEmpty()) {

                // Parsing the input date and formatting it to the required format
                val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                val outputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)

                val releaseDate: String
                try {
                    val date: Date = inputDateFormat.parse(releaseDateStr)!!
                    releaseDate = outputDateFormat.format(date)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Invalid date format. Please use yyyy-MM-dd.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val album = Album(
                    name = name,
                    cover = cover,
                    releaseDate = releaseDate,
                    description = description,
                    genre = genre,
                    recordLabel = recordLabel
                )

                Log.d("AlbumCreateFragment", "Creating album with data: $album")

                albumViewModel.createAlbum(album)
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }

        albumViewModel.albumCreateResult.observe(viewLifecycleOwner, Observer { result ->
            result.fold(
                onSuccess = {
                    Toast.makeText(requireContext(), "Album created successfully", Toast.LENGTH_SHORT).show()
                    Log.d("AlbumCreateFragment", "Album created successfully: $it")
                    // Limpiar los campos o redirigir al usuario
                    clearFields()
                    // Redirigir al usuario, si es necesario
                    // findNavController().navigate(R.id.action_albumCreateFragment_to_homeFragment)
                },
                onFailure = {
                    Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    Log.e("AlbumCreateFragment", "Error creating album", it)
                }
            )
        })

        albumReleaseDateInput.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar.time)

            albumReleaseDateInput.setText(formattedDate)
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun clearFields() {
        albumCoverInput.text.clear()
        albumDescriptionInput.text.clear()
        // No es necesario limpiar los Spinners
        albumNameInput.text.clear()
        albumReleaseDateInput.text.clear()
    }
}



