package com.scallop.awesomevibes.ui.artists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.common.BaseFragment
import com.scallop.awesomevibes.databinding.FragmentArtistsBinding
import com.scallop.awesomevibes.entities.Artist
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.ui.commons.EndlessRecyclerViewScrollListener
import com.scallop.awesomevibes.ui.commons.OnItemClick
import com.scallop.awesomevibes.ui.commons.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArtistsFragment : BaseFragment(), OnItemClick<Artist> {

    private val mViewModel: ArtistsViewModel by viewModel {
        parametersOf(arguments?.let {
            val passedArguments = ArtistsFragmentArgs.fromBundle(it)
            passedArguments.searchName
        })
    }

    private var mBinding: FragmentArtistsBinding? = null

    private lateinit var mAdapter: ArtistsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mEndlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = ArtistsAdapter(this)
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

        mBinding?.let {
            with(it) {
                artistList.adapter = mAdapter
                mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                mEndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(
                    mLayoutManager,
                    STARTING_PAGE_INDEX
                ) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                        mViewModel.getArtists(page)
                    }
                }
                artistList.layoutManager = mLayoutManager
                artistList.addOnScrollListener(mEndlessRecyclerViewScrollListener)
            }
        }

        mAdapter.clear()

        mViewModel.data.observe(viewLifecycleOwner, {
            when (it.responseType) {
                Status.LOADING -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, true) }
                }
                Status.SUCCESSFUL -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }
                    it.data?.let { it1 ->
                        updateList(it1)
                    }
                }
                Status.ERROR -> {
                    Log.d("", "")
                }
            }
        })
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    override fun onItemClicked(item: Artist) {
        val action = ArtistsFragmentDirections.searchAlbums()
        action.searchArtist = item.artistName
        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    private fun updateList(items: List<Artist>) {
        mAdapter.updateList(items)

        if (mAdapter.itemCount == 0) {
            mBinding?.emptyLabel?.visible(true)
            mBinding?.artistList?.visible(false)
        } else {
            mBinding?.emptyLabel?.visible(false)
            mBinding?.artistList?.visible(true)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}