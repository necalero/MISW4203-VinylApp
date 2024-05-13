package com.vinyl.app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinyl.app.R
import com.vinyl.app.activities.CollectorDetailActivity
import com.vinyl.app.adapters.CollectorListAdapter
import com.vinyl.app.databinding.FragmentCollectorListBinding
import com.vinyl.app.pojo.Collector
import com.vinyl.app.viewmodel.CollectorViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinyl.app.viewmodel.CollectorViewModelFactory

class CollectorListFragment : Fragment() {


    private lateinit var binding: FragmentCollectorListBinding
    private lateinit var collectorMVVM: CollectorViewModel
    private lateinit var collectorListAdapter: CollectorListAdapter

    companion object {
        const val COLLECTOR_ID = "com.vinyl.app.fragments.id"
        const val COLLECTOR_NAME = "com.vinyl.app.fragments.name"
        const val COLLECTOR_PHONE = "com.vinyl.app.fragments.birthdate"
        const val COLLECTOR_EMAIL = "com.vinyl.app.fragments.description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CollectorViewModelFactory(requireContext())
        collectorMVVM = ViewModelProvider(this, factory)[CollectorViewModel::class.java]
        collectorListAdapter = CollectorListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        prepareListRecyclerView()

        collectorMVVM.getCollectors()
        observeCollectors()

//        loadCollectors()


        onCollectorClick()

    }

    private fun onCollectorClick() {
        collectorListAdapter.onItemClick = { collector ->
            val intent = Intent(activity, CollectorDetailActivity::class.java)
            intent.putExtra(COLLECTOR_ID, collector.id)
            intent.putExtra(COLLECTOR_NAME, collector.name)
            intent.putExtra(COLLECTOR_PHONE, collector.telephone)
            intent.putExtra(COLLECTOR_EMAIL, collector.email)
            startActivity(intent)

        }
    }


    private fun prepareListRecyclerView() {
        binding.recViewCollectors.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = collectorListAdapter


        }
    }

    private fun observeCollectors() {
        collectorMVVM.observeCollectorsLiveData().observe(viewLifecycleOwner
        ) { collectorList ->
            collectorListAdapter.setCollectors(collectorList = collectorList as ArrayList<Collector>) }
    }
}