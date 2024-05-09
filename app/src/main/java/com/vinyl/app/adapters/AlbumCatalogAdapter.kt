package com.vinyl.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinyl.app.databinding.AlbumCatalogBinding
import com.vinyl.app.pojo.Album
class AlbumCatalogAdapter(private val onAlbumClick: (Album) -> Unit) : RecyclerView.Adapter<AlbumCatalogAdapter.AlbumCatalogViewHolder>() {

    private var albumList = ArrayList<Album>()

    fun setAlbums(albumList: ArrayList<Album>){
        this.albumList = albumList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumCatalogViewHolder {
        val binding = AlbumCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumCatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumCatalogViewHolder, position: Int) {
        val album = albumList[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(album.cover)
                .into(albumImg)
            albumNameTv.text = album.name

            root.setOnClickListener {
                onAlbumClick(album)
            }
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    class AlbumCatalogViewHolder(var binding: AlbumCatalogBinding): RecyclerView.ViewHolder(binding.root)
}
