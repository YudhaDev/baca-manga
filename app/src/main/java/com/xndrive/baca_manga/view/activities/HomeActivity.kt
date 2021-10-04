package com.xndrive.baca_manga.view.activities

import android.os.Bundle
import android.widget.TableLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xndrive.baca_manga.R
import com.xndrive.baca_manga.databinding.ActivityHomeBinding
import com.xndrive.baca_manga.lists.FragmentList
import com.xndrive.baca_manga.view.adapters.HomeViewpagerAdapter
import com.xndrive.baca_manga.view.fragments.AccountFragment
import com.xndrive.baca_manga.view.fragments.FavouriteFragment
import com.xndrive.baca_manga.view.fragments.HomeFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var _homeBinding: ActivityHomeBinding
    private val listFragmentDiHome = mutableListOf<FragmentList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(_homeBinding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        determine()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun determine() {
        //adding view fragment for home activity
        listFragmentDiHome.add(FragmentList("Beranda", R.drawable.ic_home, HomeFragment()))
        listFragmentDiHome.add(FragmentList("Favorit", R.drawable.ic_favorite, FavouriteFragment()))
        listFragmentDiHome.add(FragmentList("Akun" , R.drawable.ic_account, AccountFragment()))
        _homeBinding.homeViewpager.adapter = HomeViewpagerAdapter(this, listFragmentDiHome)
        _homeBinding.homeBottomtablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                this@HomeActivity.setTitle(listFragmentDiHome.get(tab!!.position).name)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        TabLayoutMediator(_homeBinding.homeBottomtablayout, _homeBinding.homeViewpager) {
            tab, position -> tab.text = listFragmentDiHome[position].name
            tab.icon = resources.getDrawable(listFragmentDiHome[position].icon)
        }.attach()
    }
}

