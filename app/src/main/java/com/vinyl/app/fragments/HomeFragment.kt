package com.vinyl.app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.vinyl.app.activities.AlbumCreateActivity
import com.vinyl.app.activities.AlbumDetailActivity
import com.vinyl.app.activities.CollectorDetailActivity
import com.vinyl.app.activities.CollectorListActivity
import com.vinyl.app.activities.MusicianListActivity
import com.vinyl.app.adapters.AlbumCatalogAdapter
import com.vinyl.app.databinding.FragmentHomeBinding
import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Musician
import com.vinyl.app.viewmodel.CollectorViewModel
import com.vinyl.app.viewmodel.HomeViewModel
import com.vinyl.app.viewmodel.HomeViewModelFactory
import com.vinyl.app.viewmodel.MusicianViewModel
import com.vinyl.app.viewmodel.MusicianViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMVVM: HomeViewModel
    private lateinit var albumCatalogAdapter: AlbumCatalogAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = HomeViewModelFactory(requireContext())
        homeMVVM = ViewModelProvider(this, factory)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this@HomeFragment)
            .load("https://www.billboard.com/wp-content/uploads/2022/12/metro-boomin-press-photo-2022-billboard-1548-1.jpg?w=942&h=623&crop=1")
            .into(binding.artistsImg)

        Glide.with(this@HomeFragment)
            .load("https://images.pexels.com/photos/908965/pexels-photo-908965.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
            .into(binding.collectorsImg)

        prepareAlbumListRecyclerView()

        homeMVVM.getAlbums()
        observeAlbums()

        onArtistButtonClick()
        onCollectorButtonClick()
        onCreateAlbumButtonClick()

//
//        loadAlbums()

    }

    private fun onArtistButtonClick() {
        binding.artistsButton.setOnClickListener {
            val intent = Intent(activity, MusicianListActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun onAlbumButtonClick() {
//        binding.artistsButton.setOnClickListener {
//            val intent = Intent(activity, MusicianListActivity::class.java)
//            startActivity(intent)
//        }
//    }

    private fun onCollectorButtonClick() {
        binding.collectorsButton.setOnClickListener {
            val intent = Intent(activity, CollectorListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onCreateAlbumButtonClick(){
        binding.fabCreateAlbum.setOnClickListener{
            val intent = Intent(activity, AlbumCreateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun prepareAlbumListRecyclerView() {
        albumCatalogAdapter = AlbumCatalogAdapter { album ->
            val intent = Intent(activity, AlbumDetailActivity::class.java).apply {
                putExtra("albumId", album.id)
            }
            startActivity(intent)
        }
        binding.recViewAlbums.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = albumCatalogAdapter
        }
    }

    private fun observeAlbums() {
        homeMVVM.observeAlbumsLiveData().observe(viewLifecycleOwner
        ) { albumList ->
                albumCatalogAdapter.setAlbums(albumList = albumList as ArrayList<Album>)
        }
    }



}