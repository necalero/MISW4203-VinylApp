package com.vinyl.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinyl.app.databinding.MusicianListItemBinding
import com.vinyl.app.pojo.Musician

class MusicianListAdapter:RecyclerView.Adapter<MusicianListAdapter.MusicianListViewHolder>() {

    lateinit var onItemClick:((Musician) -> Unit)
    private var musicianList = ArrayList<Musician>()

    fun setMusicians(musicianList: ArrayList<Musician>){
        this.musicianList = musicianList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianListViewHolder {
        return MusicianListViewHolder(MusicianListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MusicianListViewHolder, position: Int) {
        holder.binding.musicianNameTv.text = musicianList[position].name

        holder.itemView.setOnClickListener{
            onItemClick.invoke(musicianList[position])
        }
    }

    override fun getItemCount(): Int {
        return musicianList.size
    }



    class MusicianListViewHolder(var binding: MusicianListItemBinding):RecyclerView.ViewHolder(binding.root)

}