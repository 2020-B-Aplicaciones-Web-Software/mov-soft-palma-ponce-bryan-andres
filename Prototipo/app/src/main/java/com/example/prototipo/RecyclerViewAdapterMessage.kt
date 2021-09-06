package com.example.prototipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterMessage(
    private val contexto: Class<*>,
    private val listaMessage: List<MessageClass>,
    private val recyclerView: RecyclerView,
): RecyclerView.Adapter<RecyclerViewAdapterMessage.MyViewHolder>()  {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var header: TextView = view.findViewById(R.id.message_header)
        var info: TextView = view.findViewById(R.id.message_info)
        var user: TextView = view.findViewById(R.id.message_user)
        var photo: ImageView=view.findViewById(R.id.message_icon)

        init {
            photo.setImageResource(R.drawable.mail)
        }
    }


    override fun getItemCount(): Int {
        return listaMessage.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message = listaMessage[position]

        holder.header.text=message.header
        holder.info.text= message.info
        holder.user.text=message.user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.message,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
}