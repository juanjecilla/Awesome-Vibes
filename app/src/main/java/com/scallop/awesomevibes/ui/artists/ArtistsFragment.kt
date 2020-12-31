package com.scallop.awesomevibes.ui.artists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.FragmentArtistsBinding
import com.scallop.awesomevibes.entities.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtistsFragment : Fragment() {

    private val mViewModel: ArtistsViewModel by viewModel()
    private var mBinding: FragmentArtistsBinding? = null

    private lateinit var mAdapter: ArtistsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = ArtistsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentArtistsBinding.bind(view)

        arguments?.let {
            val passedArguments = ArtistsFragmentArgs.fromBundle(it)
            mViewModel.getArtists(passedArguments.searchName)
        }

        mBinding?.let {
            with(it) {
                artistsList.adapter = mAdapter
                artistsList.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    override fun onStart() {
        super.onStart()
        mViewModel.data.observe(this, {
            when (it.responseType) {
                Status.LOADING -> {
                    Log.d("", "")
                }
                Status.SUCCESSFUL -> {
                    it.data?.let { it1 -> mAdapter.updateList(it1) }
                }
                Status.ERROR -> {
                    Log.d("", "")
                }
            }
        })
    }
}