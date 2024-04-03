package com.cis436_project3.p3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cis436_project3.p3.databinding.FragmentTopBinding

class TopFragment : Fragment() {

    private var _binding: FragmentTopBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //alternative to line below
     //  viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        /*
        //FOR EXAMPLE FROM LECTURE 9
        val resultObserver = Observer<Float> {
            result -> binding.resultText.text = result.toString()
        }
        viewModel.getResult().observe(viewLifecycleOwner, resultObserver)
      //  binding.resultText.text = viewModel.getResult().toString()

         */

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}