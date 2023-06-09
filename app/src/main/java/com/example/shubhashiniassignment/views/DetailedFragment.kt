package com.example.shubhashiniassignment.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shubhashiniassignment.R
import com.example.shubhashiniassignment.databinding.FragmentDetailedBinding
import com.squareup.picasso.Picasso

class DetailedFragment : Fragment() {
    lateinit var binding: FragmentDetailedBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("ID") ?: 0
        val title = arguments?.getString("TITLE") ?: ""
        val url = arguments?.getString("URL") ?: ""

        binding.txtDAlbumTitle.text = "$id. $title"
        Picasso.get()
            .load(url)
            .fit()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgThumb)
    }
}