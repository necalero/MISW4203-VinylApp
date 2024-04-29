package com.vinyl.app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        musicianMVVM = ViewModelProviders.of(this)[MusicianViewModel::class.java]
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

        musicianMVVM.getMusicians()
        observeMusicians()

//        loadMusicians()


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
            startActivity(intent)

        }
    }


    private fun prepareListRecyclerView() {
        binding.recViewMusicians.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = musicianListAdapter


        }
    }

    private fun observeMusicians() {
        musicianMVVM.observeMusiciansLiveData().observe(viewLifecycleOwner
        ) { musicianList ->
            musicianListAdapter.setMusicians(musicianList = musicianList as ArrayList<Musician>) }
    }
}