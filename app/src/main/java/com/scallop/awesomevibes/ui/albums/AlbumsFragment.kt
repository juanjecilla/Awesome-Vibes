package com.scallop.awesomevibes.ui.albums

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
import com.scallop.awesomevibes.databinding.FragmentAlbumsBinding
import com.scallop.awesomevibes.entities.Album
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.ui.commons.EndlessRecyclerViewScrollListener
import com.scallop.awesomevibes.ui.commons.OnItemClick
import com.scallop.awesomevibes.ui.commons.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AlbumsFragment : BaseFragment(), OnItemClick<Album> {

    private val mViewModel: AlbumsViewModel by viewModel {
        parametersOf(arguments?.let {
            val passedArguments = AlbumsFragmentArgs.fromBundle(it)
            passedArguments.searchArtist
        })
    }

    private var mBinding: FragmentAlbumsBinding? = null

    private lateinit var mAdapter: AlbumsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mEndlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = AlbumsAdapter(this)
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

        mAdapter.clear()

        mBinding?.let {
            with(it) {
                mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                mEndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(
                    mLayoutManager,
                    STARTING_PAGE_INDEX
                ) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                        mViewModel.getAlbums(page)
                    }
                }
                albumList.adapter = mAdapter
                albumList.layoutManager = mLayoutManager
                albumList.addOnScrollListener(mEndlessRecyclerViewScrollListener)
            }
        }

        mViewModel.data.observe(viewLifecycleOwner, {
            when (it.responseType) {
                Status.LOADING -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, true) }
                }
                Status.SUCCESSFUL -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }
                    it.data?.let { it1 -> updateList(it1) }
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

    override fun onItemClicked(item: Album) {
        val action = AlbumsFragmentDirections.searchSongs()
        action.searchAlbum = item.collectionName
        action.searchAlbumId = item.collectionId
        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    private fun updateList(items: List<Album>) {
        mAdapter.updateList(items)

        if (mAdapter.itemCount == 0) {
            mBinding?.emptyLabel?.visible(true)
            mBinding?.albumList?.visible(false)
        } else {
            mBinding?.emptyLabel?.visible(false)
            mBinding?.albumList?.visible(true)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}