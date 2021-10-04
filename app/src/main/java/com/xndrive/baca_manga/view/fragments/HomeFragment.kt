package com.xndrive.baca_manga.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xndrive.baca_manga.R
import com.xndrive.baca_manga.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _homeFragmentBinding: FragmentHomeBinding? = null
    val name = "Home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _homeFragmentBinding = FragmentHomeBinding.inflate(requireActivity().layoutInflater, container, false)
        //show shimmer
        showShimmer()
        showBanner()
        // Inflate the layout for this fragment
        return _homeFragmentBinding!!.root
    }

    private fun showBanner() {
        val viewpager = _homeFragmentBinding!!.fragmentHomeBanner
        _homeFragmentBinding!!.fragmentHomeBannerIndicator.setViewPager(viewpager)
    }

    private fun showShimmer() {
        _homeFragmentBinding!!.fragmentHomeShimmer.visibility = View.VISIBLE
        _homeFragmentBinding!!.fragmentHomeShimmer.showShimmer(true)
        _homeFragmentBinding!!.fragmentHomeShimmer.startShimmer()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_appbar_menu, menu)

        val searchView:androidx.appcompat.widget.SearchView = menu.findItem(R.id.menu_home_appbar_search).actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireActivity(), "disubmit", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("pap nilai boolean: $newText")
                return true
            }

        })
        menu.findItem(R.id.menu_home_appbar_search).setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                Toast.makeText(requireActivity(), "expanded", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                Toast.makeText(requireActivity(), "collapsed", Toast.LENGTH_SHORT).show()
                return true
            }

        })
        searchView.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                println("pap nilai boolean: $hasFocus")
                if (hasFocus){
                    Toast.makeText(requireActivity(), "search diklik", Toast.LENGTH_SHORT).show()
                }
            }

        })
//        val searchbar: LinearLayout = searchView.findViewById(R.id.search_bar)
//        searchbar.layoutTransition = LayoutTransition()
//        searchbar.setTransitionVisibility(View.VISIBLE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home_appbar_about -> {
                Toast.makeText(requireActivity(), "about diklik", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_home_appbar_disclaimer -> {
                Toast.makeText(requireActivity(), "disclaimer diklik", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_home_appbar_notification -> {
                Toast.makeText(requireActivity(), "notification diklik", Toast.LENGTH_SHORT).show()
                val popupMenu = PopupMenu(requireActivity(), requireActivity().findViewById(R.id.menu_home_appbar_notification))
                popupMenu.menuInflater.inflate(R.menu.home_secondary_menu, popupMenu.menu)
                popupMenu.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _homeFragmentBinding = null
    }

}