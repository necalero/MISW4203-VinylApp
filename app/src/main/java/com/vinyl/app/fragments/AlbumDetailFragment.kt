package com.vinyl.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vinyl.app.R
import com.vinyl.app.databinding.FragmentAlbumDetailBinding
import com.vinyl.app.databinding.FragmentHomeBinding
import com.vinyl.app.pojo.Album
import com.vinyl.app.viewmodel.AlbumViewModel
class AlbumDetailFragment : Fragment() {
    private lateinit var viewModel: AlbumViewModel
    private lateinit var binding: FragmentAlbumDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)

        val albumId = arguments?.getString("albumId") ?: return
        viewModel.getAlbum(albumId)

        viewModel.observeAlbumLiveData().observe(viewLifecycleOwner) { album ->
            updateUI(view, album)
        }
    }

    private fun updateUI(view: View, album: Album) {
        val albumName: TextView = view.findViewById(R.id.albumName)
        val albumGenre: TextView = view.findViewById(R.id.albumGenre)
        val albumDescription: TextView = view.findViewById(R.id.albumDescription)
        val albumCover: ImageView = view.findViewById(R.id.albumCover)
        val tracksLayout: LinearLayout = view.findViewById(R.id.tracksLayout)

        albumName.text = album.name
        albumGenre.text = album.genre
        albumDescription.text = album.description

        Glide.with(this).load(album.cover).into(albumCover)

        tracksLayout.removeAllViews()

        album.tracks?.forEach { track ->
            val trackView = LayoutInflater.from(context).inflate(R.layout.track_item, tracksLayout, false)
            val trackName: TextView = trackView.findViewById(R.id.trackName)
            val trackDuration: TextView = trackView.findViewById(R.id.trackDuration)
            trackName.text = track.name
            trackDuration.text = track.duration
            tracksLayout.addView(trackView)
        }
    }
}
