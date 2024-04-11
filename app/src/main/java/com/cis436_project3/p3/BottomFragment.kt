package com.cis436_project3.p3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class BottomFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottom, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.catDetails.observe(viewLifecycleOwner) { details ->
            view.findViewById<TextView>(R.id.tvCatName).text = details.name
            view.findViewById<TextView>(R.id.tvCatDescription).text = details.description
            view.findViewById<TextView>(R.id.tvCatOrigin).text = details.origin
            view.findViewById<TextView>(R.id.tvCatTemp).text = details.temperament
        }
        return view
    }
}
