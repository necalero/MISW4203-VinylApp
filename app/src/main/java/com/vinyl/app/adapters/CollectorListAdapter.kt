package com.vinyl.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinyl.app.R
import com.vinyl.app.pojo.Collector

class CollectorListAdapter : RecyclerView.Adapter<CollectorListAdapter.CollectorListViewHolder>() {

    lateinit var onItemClick: ((Collector) -> Unit)
    private var collectorList = ArrayList<Collector>()

    fun setCollectors(collectorList: ArrayList<Collector>) {
        this.collectorList = collectorList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collector, parent, false)
        return CollectorListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorListViewHolder, position: Int) {
        val collector = collectorList[position]
        holder.bind(collector)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(collector)
        }
    }

    override fun getItemCount(): Int {
        return collectorList.size
    }

    inner class CollectorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val collectorImage: ImageView = itemView.findViewById(R.id.collector_image)
        private val collectorName: TextView = itemView.findViewById(R.id.collector_name)
        private val collectorEmail: TextView = itemView.findViewById(R.id.collector_email)
        private val collectorTelephone: TextView = itemView.findViewById(R.id.collector_telephone)

        fun bind(collector: Collector) {
            collectorName.text = collector.name
            collectorEmail.text = collector.email
        }
    }
}