package com.vinyl.app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vinyl.app.R
import com.vinyl.app.activities.MusicianDetailActivity
import com.vinyl.app.activities.MusicianListActivity
import com.vinyl.app.adapters.MusicianListAdapter
import com.vinyl.app.databinding.FragmentHomeBinding
import com.vinyl.app.databinding.FragmentMusicianListBinding
import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Musician
import com.vinyl.app.viewmodel.MusicianViewModel


class MusicianListFragment : Fragment() {

    private lateinit var binding: FragmentMusicianListBinding
    private lateinit var musicianMVVM: MusicianViewModel
    private lateinit var musicianListAdapter: MusicianListAdapter

    companion object {
        const val MUSICIAN_ID = "com.vinyl.app.fragments.id"
        const val MUSICIAN_NAME = "com.vinyl.app.fragments.name"
        const val MUSICIAN_BIRTHDATE = "com.vinyl.app.fragments.birthdate"
        const val MUSICIAN_DESCRIPTION = "com.vinyl.app.fragments.description"
        const val MUSICIAN_IMAGE = "com.vinyl.app.fragments.image"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    homeMVVM = ViewModelProviders.of(this).get(MusicianViewModel::class.java)
        musicianListAdapter = MusicianListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicianListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        prepareListRecyclerView()

        //TODO: Implement retrofit logic to load musician cards
        /*musicianMVVM.getMusicians()
        observeMusicians()*/

        loadMusicians()


        onMusicianClick()

    }

    private fun onMusicianClick() {
        musicianListAdapter.onItemClick = { musician ->
            val intent = Intent(activity, MusicianDetailActivity::class.java)
            intent.putExtra(MUSICIAN_ID, musician.id)
            intent.putExtra(MUSICIAN_NAME, musician.name)
            intent.putExtra(MUSICIAN_DESCRIPTION, musician.description)
            intent.putExtra(MUSICIAN_BIRTHDATE, musician.birthDate)
            intent.putExtra(MUSICIAN_IMAGE, musician.image)

        }
    }

    private fun loadMusicians() {

        // Create an empty list to store the mock musicians
        val mockMusicians = ArrayList<Musician>()

        // Add some mock musicians to the list
        mockMusicians.add(
            Musician(
                id = "1",
                birthDate = "1992-08-19",
                description = "Renowned Colombian singer and songwriter known for his smooth vocals and captivating melodies, blending reggaeton, Latin pop, and urban styles.",
                image = "https://yt3.googleusercontent.com/GUW78kIdMM2mVjl-c1rkSD8DqNYSRZAfTUTie3j4xKFN6agTpdb9UiMDGwQB2yuoDpKB1a8QNn8=s900-c-k-c0x00ffffff-no-rj",
                name = "Feid"
            )
        )
        mockMusicians.add(
            Musician(
                id = "2",
                birthDate = "1991-02-14",
                description = "Chart-topping singer-songwriter celebrated for her empowering lyrics and vibrant stage presence, a leading figure in the Latin music industry.",
                image = "https://upload.wikimedia.org/wikipedia/commons/c/cb/2023-11-16_Gala_de_los_Latin_Grammy%2C_15.jpg",
                name = "Karol G"
            )
        )
        mockMusicians.add(
            Musician(
                id = "3",
                birthDate = "1990-08-19",
                description = "Multi-talented artist known for his unique blend of Latin trap and reggaeton, pushing boundaries with his innovative sound.",
                image = "https://yt3.googleusercontent.com/MH1BdZQE2X6k3WlIns2YlyBNude9Qh3nN0dnG-_zvkM1o5gzBS3upL-C2w6Xm6DysPyg4x8ZHhQ=s900-c-k-c0x00ffffff-no-rj",
                name = "Bad Bunny"
            )
        )

        mockMusicians.add(
            Musician(
                id = "4",
                birthDate = "1999-06-27",
                description = "Puerto Rican singer and songwriter known for his versatile style, blending reggaeton, trap, and R&B influences.",
                image = "https://www.latercera.com/resizer/p_y3YQy7Np9-T8W9rpGXC8jrSdA=/900x600/smart/cloudfront-us-east-1.images.arcpublishing.com/copesa/E3YIDFMCBBCQNFJJXGLIE6WILI.jpg",
                name = "Mora"
            )
        )

        mockMusicians.add(
            Musician(
                id = "5",
                birthDate = "1996-09-15",
                description = "Emerging Latin artist celebrated for his energetic performances and dynamic rap flow, gaining recognition for his unique sound.",
                image = "https://es.rollingstone.com/wp-content/uploads/2024/03/YOVNGCHIMI-y-la-nueva-generacion-del-drill-2.jpg",
                name = "YOVNGCHIMI"
            )
        )

        mockMusicians.add(
            Musician(
                id = "6",
                birthDate = "1998-11-03",
                description = "Talented rapper and singer known for his infectious charisma and compelling storytelling, making waves in the Latin music scene.",
                image = "https://hips.hearstapps.com/hmg-prod/images/photo-feb-16-2024-7-49-10-pm-660f6efbe410b.jpg",
                name = "Young Miko"
            )
        )







        musicianListAdapter.setMusicians(mockMusicians)
    }

    private fun prepareListRecyclerView() {
        binding.recViewMusicians.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = musicianListAdapter


        }
    }

    private fun observeAlbums() {
        musicianMVVM.observeMusiciansLiveData().observe(viewLifecycleOwner, object: Observer<List<Musician>> {
            override fun onChanged(value: List<Musician>) {
                TODO("Implement logic to load cards")
            }

        })
    }
}