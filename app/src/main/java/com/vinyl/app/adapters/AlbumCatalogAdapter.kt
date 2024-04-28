package com.vinyl.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinyl.app.databinding.AlbumCatalogBinding
import com.vinyl.app.pojo.Album

class AlbumCatalogAdapter:RecyclerView.Adapter<AlbumCatalogAdapter.AlbumCatalogViewHolder>() {


    private var albumList = ArrayList<Album>()

    fun setAlbums(albumList: ArrayList<Album>){
        this.albumList = albumList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumCatalogViewHolder {
        return AlbumCatalogViewHolder(AlbumCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AlbumCatalogViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(albumList[position].cover)
            .into(holder.binding.albumImg)
        holder.binding.albumNameTv.text = albumList[position].name
    }

    override fun getItemCount(): Int {
        return albumList.size
    }



    class AlbumCatalogViewHolder(var binding: AlbumCatalogBinding):RecyclerView.ViewHolder(binding.root)

}