package com.vinyl.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinyl.app.R
import com.vinyl.app.databinding.MusicianListItemBinding
import com.vinyl.app.pojo.Musician

class MusicianListAdapter : RecyclerView.Adapter<MusicianListAdapter.MusicianListViewHolder>() {

    lateinit var onItemClick: ((Musician) -> Unit)
    private var musicianList = ArrayList<Musician>()

    fun setMusicians(musicianList: ArrayList<Musician>) {
        this.musicianList = musicianList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_musician, parent, false)
        return MusicianListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicianListViewHolder, position: Int) {
        val musician = musicianList[position]
        holder.bind(musician)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(musician)
        }
    }

    override fun getItemCount(): Int {
        return musicianList.size
    }

    inner class MusicianListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val musicianImage: ImageView = itemView.findViewById(R.id.musician_image)
        private val musicianName: TextView = itemView.findViewById(R.id.musician_name)
        private val musicianDescription: TextView = itemView.findViewById(R.id.musician_description)

        fun bind(musician: Musician) {
            musicianName.text = musician.name
            musicianDescription.text = musician.birthDate.split("-").first()
            Glide.with(itemView.context)
                .load(musician.image)
                .circleCrop()
                .into(musicianImage)
        }
    }
}