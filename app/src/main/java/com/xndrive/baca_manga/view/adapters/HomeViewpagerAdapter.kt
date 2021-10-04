package com.xndrive.baca_manga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xndrive.baca_manga.lists.FragmentList
import com.xndrive.baca_manga.view.activities.HomeActivity

class HomeViewpagerAdapter(private val activity: FragmentActivity, private val listFragment: MutableList<FragmentList>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment.get(position).fragment
    }
}