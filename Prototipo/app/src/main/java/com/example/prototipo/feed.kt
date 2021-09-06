package com.example.prototipo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class feed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val user_icon: ImageView = findViewById(R.id.user_icon)
        user_icon.setImageResource(R.drawable.user)
        val search_bar: ImageView = findViewById(R.id.search_bar)
        search_bar.setImageResource(R.drawable.search)
        val coin: ImageView = findViewById(R.id.coin_icon)
        coin.setImageResource(R.drawable.coin)
        val bar:ImageView=findViewById(R.id.feed_bar)
        bar.setImageResource(R.drawable.active_home)
        bar.setOnClickListener {
            startActivity(Intent(this, explore::class.java))
        }

        val posts = arrayListOf<Post>()
        val postnames = arrayListOf(
            "Townhouse in DC has a cute little model townhouse in its front yard",
            "Top five causes of police officer deaths per year vs COVID 1825-2020",
            "Largest producers of renewable energy from 1965 to 2020",
            "The spilled yoke from my fried egg looks like a chick",
            "This car is decorated with old floppy disks",
        )
        val subnames = arrayListOf(
            "r/AskReddit",
            "r/Nature",
            "r/Food",
            "r/MildlyInteresting",
            "r/Horror",
        )
        val postdata = arrayListOf(
            "Publicado por u/redditUser1 • 16h • i.redd.it",
            "Publicado por u/holaHola • 6h • i.redd.it",
            "Publicado por u/Yes45si • 1d • i.redd.it",
            "Publicado por u/Maria1 • 16h • i.redd.it",
            "Publicado por u/User2 • 20h • i.redd.it",
        )

        posts
            .add(
                Post(
                    postnames[0],
                    R.drawable.post_img1,
                    postdata[0],
                    Random.nextInt(0, 999),
                    R.drawable.premios1,
                    Random.nextInt(0, 999),
                    Random.nextInt(0, 999),
                    subnames[0],
                    R.drawable.subreddit_1

                ),

                )
        posts
            .add(
                Post(
                    postnames[1],
                    R.drawable.post_img2,
                    postdata[1],
                    Random.nextInt(0, 999),
                    R.drawable.premios2,
                    Random.nextInt(0, 999),
                    Random.nextInt(0, 999),
                    subnames[1],
                    R.drawable.subreddit_2

                ),

                )
        posts
            .add(
                Post(
                    postnames[2],
                    R.drawable.post_img3,
                    postdata[2],
                    Random.nextInt(0, 999),
                    R.drawable.premios3,
                    Random.nextInt(0, 999),
                    Random.nextInt(0, 999),
                    subnames[2],
                    R.drawable.subreddit_3
                ),

                )
        posts
            .add(
                Post(
                    postnames[3],
                    R.drawable.post_img4,
                    postdata[3],
                    Random.nextInt(0, 999),
                    R.drawable.premios4,
                    Random.nextInt(0, 999),
                    Random.nextInt(0, 999),
                    subnames[3],
                    R.drawable.subreddit_4
                ),

                )
        posts
            .add(
                Post(
                    postnames[4],
                    R.drawable.post_img4,
                    postdata[4],
                    Random.nextInt(0, 999),
                    R.drawable.premios4,
                    Random.nextInt(0, 999),
                    Random.nextInt(0, 999),
                    subnames[4],
                    R.drawable.subreddit_5
                ),

                )
        val recyclerViewAdapterPost = findViewById<RecyclerView>(
            R.id.recycler_view_posts
        )
        iniciarRV(
            posts,
            this,
            recyclerViewAdapterPost
        )
    }

    fun iniciarRV(
        lista: List<Post>,
        actividad: feed,
        recyclerView: RecyclerView
    ) {
        val adaptador = RecyclerViewAdapterPost(
            actividad::class.java,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
    }
}