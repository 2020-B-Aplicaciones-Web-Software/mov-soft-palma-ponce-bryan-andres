package com.example.prototipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterNotification(
    private val contexto: Class<*>,
    private val listaNotification: List<Notification>,
    private val recyclerView: RecyclerView,
) : RecyclerView.Adapter<RecyclerViewAdapterNotification.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var sub_photo: ImageView = view.findViewById(R.id.sub_photo)
        var sub_action: ImageView = view.findViewById(R.id.action_photo)
        var notif_info: TextView = view.findViewById(R.id.notif_info)
        var notif_content: TextView = view.findViewById(R.id.notif_content)

        init {
        }
    }


    override fun getItemCount(): Int {
        return listaNotification.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sub = listaNotification[position]

        holder.sub_photo.setImageResource(sub.sub_photo)
        holder.sub_action.setImageResource(sub.sub_action)
        holder.notif_info.text=sub.notif_info
        holder.notif_content.text=sub.notif_content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.notification,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
}