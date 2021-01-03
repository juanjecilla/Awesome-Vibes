package com.scallop.awesomevibes.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var mBinding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSearchBinding.bind(view)

        mBinding?.let {
            with(it) {
                next.setOnClickListener { _ -> searchArtist(it.searchBar.text.toString()) }
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun searchArtist(name: String) {
        if (name.isNotEmpty()) {
            val action = SearchFragmentDirections.searchArtist()
            action.searchName = name
            val navController = view?.findNavController()
            navController?.navigate(action)
        } else {
            mBinding?.root?.let { Snackbar.make(it, R.string.empty_search, Snackbar.LENGTH_SHORT).show() }
        }
    }
}