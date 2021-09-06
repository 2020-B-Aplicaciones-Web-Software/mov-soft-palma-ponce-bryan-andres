package com.example.prototipo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class fragment_message : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val subs = arrayListOf<MessageClass>()
        val header = arrayListOf(
            "Your r/Food submission",
            "Your r/MildlyInteresting",
            "invitation",
            "AutoModerator notification",
        )
        val content = arrayListOf(
            "Nice!",
            "Cool Pic!",
            "Hello",
            "I like it!",
            "That's amazing",
        )
        val date = arrayListOf(
            "u/User1 • 1d",
            "u/Human • 2d",
            "u/Moderator • 2m",
            "u/Person • 3h",
        )

        for (i in 1..30) {
            subs.add(
                MessageClass(
                    header[Random.nextInt(0,4)],
                    content[Random.nextInt(0,5)],
                    date[Random.nextInt(0,4)]
                )
            )
        }
        val view=inflater.inflate(R.layout.fragment_message2, container, false)
        val recyclerViewAdapterExplore = view?.findViewById<RecyclerView>(R.id.recycler_view_message)
        Log.i("reddit","$recyclerViewAdapterExplore")
        if (recyclerViewAdapterExplore != null) {
            iniciarRV(
                subs,
                this,
                recyclerViewAdapterExplore
            )
        }
        return view


    }
    fun iniciarRV(
        lista: List<MessageClass>,
        actividad: fragment_message,
        recyclerView: RecyclerView
    ) {
        val adaptador = RecyclerViewAdapterMessage(
            actividad::class.java,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad.activity)
    }

}