package com.vinyl.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.vinyl.app.R
import com.vinyl.app.adapters.MusicianListAdapter
import com.vinyl.app.databinding.FragmentMusicianDetailBinding
import com.vinyl.app.databinding.FragmentMusicianListBinding
import com.vinyl.app.viewmodel.MusicianViewModel


class MusicianDetailFragment : Fragment() {

    private lateinit var binding: FragmentMusicianDetailBinding
    private lateinit var musicianMVVM: MusicianViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMusicianDetailBinding.inflate(layoutInflater)
        //musicianMVVM = ViewModelProviders.of(this).get(MusicianViewModel::class.java)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicianDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)



    }
}