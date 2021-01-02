package com.scallop.awesomevibes.ui.songs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.common.BaseFragment
import com.scallop.awesomevibes.databinding.FragmentSongsBinding
import com.scallop.awesomevibes.entities.Song
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.ui.commons.EndlessRecyclerViewScrollListener
import com.scallop.awesomevibes.ui.commons.Utils
import com.scallop.awesomevibes.ui.player.OptionsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SongsFragment : BaseFragment(), OnSongItemInteractor {

    private val mViewModel: SongsViewModel by viewModel()
    private var mBinding: FragmentSongsBinding? = null

    private lateinit var mAdapter: SongsAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    private lateinit var mSearchAlbum: String
    private var mSearchAlbumId: Long = 0

    private lateinit var mEndlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    private var mSelectedSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = SongsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSongsBinding.bind(view)

        arguments?.let {
            val passedArguments = SongsFragmentArgs.fromBundle(it)
            mSearchAlbum = passedArguments.searchAlbum
            mSearchAlbumId = passedArguments.searchAlbumId

            mViewModel.getSongs(passedArguments.searchAlbum, passedArguments.searchAlbumId)
        }

        setFragmentResultListener(OptionsFragment.SELECTED_ACTION) { key, bundle ->
            when (bundle["data"]) {
                OptionsFragment.PLAY_ACTION -> mSelectedSong?.let { mViewModel.playSong(it.previewUrl) }
                OptionsFragment.SHARE_ACTION -> Utils.shareSong(
                    context,
                    mSelectedSong?.trackViewUrl
                )
            }
        }

        mBinding?.let {
            with(it) {
                songList.adapter = mAdapter
                mLayoutManager = GridLayoutManager(context, 2)
                mEndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(
                    mLayoutManager,
                    STARTING_PAGE_INDEX
                ) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                        mViewModel.getSongs(mSearchAlbum, mSearchAlbumId, page)
                    }
                }

                songList.layoutManager = mLayoutManager
                songList.addOnScrollListener(mEndlessRecyclerViewScrollListener)
            }
        }

        mViewModel.data.observe(viewLifecycleOwner, {
            when (it.responseType) {
                Status.LOADING -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, true) }
                }
                Status.SUCCESSFUL -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }
                    it.data?.let { it1 -> mAdapter.updateList(it1) }
                }
                Status.ERROR -> {
                    Log.d("", "")
                }
            }
        })
    }

    override fun onDestroyView() {
        mBinding = null
        mViewModel.stopSong()
        super.onDestroyView()
    }

    override fun onItemClicked(song: Song) {
        mSelectedSong = song
        val action = SongsFragmentDirections.showPlayer()
        action.mediaUrl = song.previewUrl
        action.isVideo = false
        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}