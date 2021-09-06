package com.example.prototipo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo.adapters.ViewPagerAdapter
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.tabs.TabLayout
import java.lang.NullPointerException

class message : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        this.supportActionBar!!.title=("Bandeja de entrada")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        val bar: ImageView =findViewById(R.id.message_bar)
        bar.setImageResource(R.drawable.active_notif)
        bar.setOnClickListener {
            startActivity(Intent(this, feed::class.java))
        }
        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val viewPager=findViewById<ViewPager>(R.id.viewPager)
        val tabs=findViewById<TabLayout>(R.id.tabs)
        adapter.addFragment(fragment_notification(),"Notificaciones")
        adapter.addFragment(fragment_message(),"Mensajes")
        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)


    }

}