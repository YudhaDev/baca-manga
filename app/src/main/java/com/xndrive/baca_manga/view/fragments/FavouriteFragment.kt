package com.xndrive.baca_manga.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xndrive.baca_manga.R
import com.xndrive.baca_manga.databinding.FragmentFavouriteBinding
import com.xndrive.baca_manga.databinding.NoticeKoneksiBermasalahBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val _notice_jaringan_bermasalah_viewid : Int = R.layout.notice_koneksi_bermasalah
private var _favouritefragmentbinding: FragmentFavouriteBinding? = null

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        loadUI()
    }

    private fun loadUI() {
        val backgroundExecutor = Executors.newSingleThreadScheduledExecutor()
        backgroundExecutor.schedule({
            requireActivity().mainExecutor.execute {
                _favouritefragmentbinding!!.fragmentFavouriteShimmer.visibility = View.GONE
                val b = NoticeKoneksiBermasalahBinding.inflate(layoutInflater, _favouritefragmentbinding!!.fragmentFavouriteTroubleJaringan, true)
//                val a = requireActivity().layoutInflater.inflate(_notice_jaringan_bermasalah_viewid, _favouritefragmentbinding!!.fragmentFavouriteTroubleJaringan)
                b.root.findViewById<View>(R.id.notice_koneksi_bermasalah_imageview).setOnClickListener {
                    Toast.makeText(requireActivity(), "abjay bob", Toast.LENGTH_SHORT).show()
                }
            }
        }, 1000, TimeUnit.MILLISECONDS)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _favouritefragmentbinding = FragmentFavouriteBinding.inflate(requireActivity().layoutInflater, container, false)
        return _favouritefragmentbinding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}