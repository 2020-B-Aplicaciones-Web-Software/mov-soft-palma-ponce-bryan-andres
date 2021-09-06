package com.example.prototipo

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.lang.NullPointerException
import kotlin.random.Random

class explore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        val user_icon: ImageView = findViewById(R.id.user_icon3)
        user_icon.setImageResource(R.drawable.user)
        val search_bar: ImageView = findViewById(R.id.search_bar3)
        search_bar.setImageResource(R.drawable.search)
        val coin: ImageView = findViewById(R.id.coin_icon3)
        coin.setImageResource(R.drawable.coin)
        val bar:ImageView=findViewById(R.id.explore_bar)
        bar.setImageResource(R.drawable.active_explore)
        bar.setOnClickListener {
            startActivity(Intent(this, message::class.java))
        }



        val subs = arrayListOf<Subreddit>()
        val subnames = arrayListOf(
            "r/AskReddit",
            "r/Nature",
            "r/Food",
            "r/MildlyInteresting",
            "r/Horror",
        )
        val subphotos = arrayListOf(
            R.drawable.subreddit_1,
            R.drawable.subreddit_2,
            R.drawable.subreddit_3,
            R.drawable.subreddit_4,
            R.drawable.subreddit_5
        )

        for (i in 1..30) {
            subs.add(
                Subreddit(
                    subphotos[Random.nextInt(0, 5)],
                    subnames[Random.nextInt(0, 5)],
                )
            )
        }

        val recyclerViewAdapterExplore = findViewById<RecyclerView>(
            R.id.recycler_view_explore
        )
        iniciarRV(
            subs,
            this,
            recyclerViewAdapterExplore
        )


    }
    fun iniciarRV(
        lista: List<Subreddit>,
        actividad: explore,
        recyclerView: RecyclerView
    ) {
        val adaptador = RecyclerViewAdapterExplore(
            actividad::class.java,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
    }

}