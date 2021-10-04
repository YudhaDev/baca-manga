 package com.xndrive.baca_manga.view.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xndrive.baca_manga.R
import com.xndrive.baca_manga.databinding.DialogPilihsumbergambarBinding
import com.xndrive.baca_manga.databinding.FragmentAccountBinding
import com.xndrive.baca_manga.view.activities.HomeActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _account_fragment_binding: FragmentAccountBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun determine() {
        with(_account_fragment_binding!!){
            this.fragmentAccountUbahgambar.setOnClickListener(this@AccountFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _account_fragment_binding = FragmentAccountBinding.inflate(requireActivity().layoutInflater, container, false)

        determine()

        return _account_fragment_binding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.fragment_account_ubahgambar -> {
//                Toast.makeText(requireActivity(), "update gambar dipilih", Toast.LENGTH_SHORT).show()
                val binding = DialogPilihsumbergambarBinding.inflate(layoutInflater)
                val dialog = Dialog(requireActivity())
                val drawable_space = ColorDrawable(Color.WHITE)
                val inset = InsetDrawable(drawable_space, 0)
                dialog.setContentView(binding.root)
                dialog.window!!.setBackgroundDrawable(inset)

                binding.dialogPilihsumbergambarKameraTextview.setOnClickListener {
                    Toast.makeText(requireActivity(), "kamera dipilih", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

                binding.dialogPilihsumbergambarGaleriTextview.setOnClickListener {
                    Toast.makeText(requireActivity(),"galeri dipilih", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                dialog.show()
                return
            }
        }
    }
}