package com.example.prototipo

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterPost(
    private val contexto: Class<*>,
    private val listaPosts: List<Post>,
    private val recyclerView: RecyclerView,

    ) : RecyclerView.Adapter<RecyclerViewAdapterPost.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var subreddit_photo: ImageView = view.findViewById(R.id.subreddit_img)
        var subreddit_name: TextView = view.findViewById(R.id.subreddit_name)
        var post_data: TextView = view.findViewById(R.id.post_data)
        var prize_num: TextView = view.findViewById(R.id.prizes_count)
        var prize_photo: ImageView = view.findViewById(R.id.prizes_img)
        var post_img: ImageView = view.findViewById(R.id.post_img)
        var post_title: TextView = view.findViewById(R.id.post_name)
        var post_likes: TextView = view.findViewById(R.id.vote_count)
        var upvote: ImageView = view.findViewById(R.id.upvote_btn)
        var downvote: ImageView= view.findViewById(R.id.downvote_btn)
        var post_comments: TextView = view.findViewById(R.id.comment_count)
        var more_menu: ImageView=view.findViewById(R.id.more_menu)
        var comment_btn: ImageView=view.findViewById(R.id.comment_btn)
        var share_btn: ImageView=view.findViewById(R.id.share_btn)
        var gift_btn: ImageView=view.findViewById(R.id.prize_btn)
        var flaglike=false
        var flagdislike=false


        init {
            upvote.setImageResource(R.drawable.upvote)
            downvote.setImageResource(R.drawable.downvote)
            more_menu.setImageResource(R.drawable.more)
            share_btn.setImageResource(R.drawable.share)
            gift_btn.setImageResource(R.drawable.gift)
            comment_btn.setImageResource(R.drawable.comment)
            upvote.setOnClickListener { this.anadirLike() }
            downvote.setOnClickListener { this.disminuirLike() }

        }

        fun anadirLike() {
            if(flaglike)
            {
                this.post_likes.text = (this.post_likes.text.toString().toInt() - 1).toString()
                this.upvote.setImageResource(R.drawable.upvote)
                flaglike=false
            }
            else{
                this.post_likes.text = (this.post_likes.text.toString().toInt() + 1).toString()
                this.upvote.setImageResource(R.drawable.upvote_pressed)
                flaglike=true
            }



        }

        fun disminuirLike() {
            if(flagdislike)
            {
                this.post_likes.text = (this.post_likes.text.toString().toInt() + 1).toString()
                this.downvote.setImageResource(R.drawable.downvote)
                flaglike=false
            }
            else{
                this.post_likes.text = (this.post_likes.text.toString().toInt() - 1).toString()
                this.downvote.setImageResource(R.drawable.downvote_pressed)
                flaglike=true
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.publicacion,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = listaPosts[position]

        holder.subreddit_photo.setImageResource(post.subreddit_img)
        holder.subreddit_name.text = post.subreddit_name
        holder.post_data.text = post.post_data
        holder.prize_num.text = post.prize_count.toString()
        holder.prize_photo.setImageResource(post.prize_img)
        holder.post_img.setImageResource(post.post_img)
        holder.post_title.text = post.post_title
        holder.post_likes.text = post.post_likes.toString()
        holder.post_comments.text = post.comment_count.toString()
    }

    override fun getItemCount(): Int {
        return listaPosts.size
    }
}