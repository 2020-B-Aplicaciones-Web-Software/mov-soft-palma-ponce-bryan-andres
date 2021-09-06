package com.example.prototipo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager
) :FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragments=ArrayList<Fragment>()
    private val fragmentNames=ArrayList<String>()
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentNames[position]
    }
    fun addFragment(fragment: Fragment, title:String){
        fragments.add(fragment)
        fragmentNames.add(title)
    }
}