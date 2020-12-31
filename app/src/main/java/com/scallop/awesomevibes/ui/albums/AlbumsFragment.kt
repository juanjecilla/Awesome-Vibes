package com.scallop.awesomevibes.ui.albums

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.FragmentAlbumsBinding
import com.scallop.awesomevibes.entities.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment() {

    private val mViewModel: AlbumsViewModel by viewModel()
    private var mBinding: FragmentAlbumsBinding? = null

    private lateinit var mAdapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = AlbumsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentAlbumsBinding.bind(view)

        arguments?.let {
            val passedArguments = AlbumsFragmentArgs.fromBundle(it)
            mViewModel.getAlbums(passedArguments.searchArtist)
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