package com.cis436_project3.p3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
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
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val spinner : Spinner? = view.findViewById(R.id.spinnerCats) ?: null

        viewModel.catBreeds.observe(viewLifecycleOwner) { breeds ->
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breeds)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner?.adapter = adapter
        }

        viewModel.fetchCatBreeds(requireContext())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}