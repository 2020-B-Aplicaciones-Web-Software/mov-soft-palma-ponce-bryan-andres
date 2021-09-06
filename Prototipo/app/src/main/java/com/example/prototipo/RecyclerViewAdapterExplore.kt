package com.example.prototipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterExplore(
    private val contexto: Class<*>,
    private val listaSubreddits: List<Subreddit>,
    private val recyclerView: RecyclerView,

    ) : RecyclerView.Adapter<RecyclerViewAdapterExplore.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var subreddit_photo: ImageView = view.findViewById(R.id.subreddit_img_explore)
        var subreddit_name: TextView = view.findViewById(R.id.subreddit_name_explore)
        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_views_subreddits,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sub = listaSubreddits[position]

        holder.subreddit_photo.setImageResource(sub.photo)
        holder.subreddit_name.text = sub.subname
    }

    override fun getItemCount(): Int {
        return listaSubreddits.size
    }
}