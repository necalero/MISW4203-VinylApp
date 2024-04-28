package com.vinyl.app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.vinyl.app.activities.MusicianDetailActivity
import com.vinyl.app.activities.MusicianListActivity
import com.vinyl.app.adapters.AlbumCatalogAdapter
import com.vinyl.app.databinding.FragmentHomeBinding
import com.vinyl.app.pojo.Album
import com.vinyl.app.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMVVM: HomeViewModel
    private lateinit var albumCatalogAdapter: AlbumCatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        homeMVVM = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        albumCatalogAdapter = AlbumCatalogAdapter()

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
            .into(binding.colectorsImg)

        prepareCatalogRecyclerView()

        //TODO: Implement retrofit logic to load album cards
        /*homeMVVM.getAlbums()
        observeAlbums()*/

        onArtistButtonClick()
        onCollectorButtonClick()


        loadAlbums()

    }

    private fun onArtistButtonClick() {
        binding.artistsButton.setOnClickListener {
            val intent = Intent(activity, MusicianListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onCollectorButtonClick() {
        
    }

    private fun loadAlbums() {

        // Create an empty list to store the mock albums
        val mockAlbums = ArrayList<Album>()

        // Add some mock albums to the list
        mockAlbums.add(
            Album(
                id = "1",
                cover = "https://i.scdn.co/image/ab67616d0000b273548f7ec52da7313de0c5e4a0",
                description = "Bad Bunny's groundbreaking album that broke Latin music records.",
                genre = "Latin Trap, Reggaeton",
                name = "YHLQMDLG",
                recordLabel = "Rimas Entertainment",
                releaseDate = "2020-02-29"
            )
        )

        mockAlbums.add(
            Album(
                id = "2",
                cover = "https://i.scdn.co/image/ab67616d0000b273f1aad814a40ec7419c234242",
                description = "Feid's critically acclaimed album showcasing his smooth vocals and catchy melodies.",
                genre = "Latin Pop, Reggaeton",
                name = "FERXXOCALIPSIS",
                recordLabel = "Universal Music Latino",
                releaseDate = "2021-03-14"
            )
        )

        mockAlbums.add(
            Album(
                id = "3",
                cover = "https://upload.wikimedia.org/wikipedia/en/9/95/Karol_G_-_KG0516.png",
                description = "Karol G's empowering album celebrating female strength and confidence.",
                genre = "Reggaeton, Latin Trap",
                name = "KG0516",
                recordLabel = "Universal Music Latino",
                releaseDate = "2021-03-25"
            )

        )

        mockAlbums.add(
            Album(
                id = "4",
                cover = "https://cdns-images.dzcdn.net/images/cover/2194275a797bd8d5ed038b61b053813a/500x500.jpg",
                description = "Mora's introspective album exploring themes of love, loss, and resilience.",
                genre = "Latin Trap, Hip-Hop",
                name = "ESTRELLA",
                recordLabel = "Rimas Entertainment",
                releaseDate = "2022-07-15"
            )
        )
        mockAlbums.add(
            Album(
                id = "5",
                cover = "https://i.scdn.co/image/ab67616d0000b273548f7ec52da7313de0c5e4a0",
                description = "Bad Bunny's groundbreaking album that broke Latin music records.",
                genre = "Latin Trap, Reggaeton",
                name = "YHLQMDLG",
                recordLabel = "Rimas Entertainment",
                releaseDate = "2020-02-29"
            )
        )

        mockAlbums.add(
            Album(
                id = "6",
                cover = "https://i.scdn.co/image/ab67616d0000b273f1aad814a40ec7419c234242",
                description = "Feid's critically acclaimed album showcasing his smooth vocals and catchy melodies.",
                genre = "Latin Pop, Reggaeton",
                name = "FERXXOCALIPSIS",
                recordLabel = "Universal Music Latino",
                releaseDate = "2021-03-14"
            )
        )

        mockAlbums.add(
            Album(
                id = "7",
                cover = "https://upload.wikimedia.org/wikipedia/en/9/95/Karol_G_-_KG0516.png",
                description = "Karol G's empowering album celebrating female strength and confidence.",
                genre = "Reggaeton, Latin Trap",
                name = "KG0516",
                recordLabel = "Universal Music Latino",
                releaseDate = "2021-03-25"
            )

        )

        mockAlbums.add(
            Album(
                id = "8",
                cover = "https://cdns-images.dzcdn.net/images/cover/2194275a797bd8d5ed038b61b053813a/500x500.jpg",
                description = "Mora's introspective album exploring themes of love, loss, and resilience.",
                genre = "Latin Trap, Hip-Hop",
                name = "ESTRELLA",
                recordLabel = "Rimas Entertainment",
                releaseDate = "2022-07-15"
            )
        )

        albumCatalogAdapter.setAlbums(mockAlbums)
    }

    private fun prepareCatalogRecyclerView() {
        binding.recViewAlbums.apply {
            layoutManager = GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
            adapter = albumCatalogAdapter


        }
    }

    private fun observeAlbums() {
        homeMVVM.observeAlbumsLiveData().observe(viewLifecycleOwner, object: Observer<List<Album>>{
            override fun onChanged(value: List<Album>) {
                TODO("Implement logic to load cards")
            }

        })
    }


}