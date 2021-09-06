package com.example.prototipo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class fragment_notification : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val subs = arrayListOf<Notification>()
        val subnames = arrayListOf(
            "u/user1 replied your comment in r/AskReddit",
            "u/user2 liked your comment in r/Nature",
            "u/user3 commented in your post in r/Food",
            "u/user2 liked your comment in r/MildlyInteresting",
            "u/user4 liked your comment in r/Horror",
        )
        val subinfo = arrayListOf(
            "Nice!",
            "Cool Pic!",
            "Hello",
            "I like it!",
            "That's amazing",
        )
        val sub_actions = arrayListOf(
            R.drawable.reply,
            R.drawable.comment,
        )

        val sub_photos = arrayListOf(
            R.drawable.subreddit_1,
            R.drawable.subreddit_2,
            R.drawable.subreddit_3,
            R.drawable.subreddit_4,
            R.drawable.subreddit_5
        )
        for (i in 1..30) {
            subs.add(
                Notification(
                    sub_photos[Random.nextInt(0, 5)],
                    sub_actions[Random.nextInt(0, 2)],
                    subinfo[Random.nextInt(0, 5)],
                    subnames[Random.nextInt(0, 5)],
                )
            )
        }
        val view=inflater.inflate(R.layout.fragment_notification, container, false)
        val recyclerViewAdapterExplore = view?.findViewById<RecyclerView>(R.id.recycler_view_notifications)
        if (recyclerViewAdapterExplore != null) {
            iniciarRV(
                subs,
                this,
                recyclerViewAdapterExplore
            )
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun iniciarRV(
        lista: List<Notification>,
        actividad: fragment_notification,
        recyclerView: RecyclerView
    ) {
        val adaptador = RecyclerViewAdapterNotification(
            actividad::class.java,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad.activity)
    }

}