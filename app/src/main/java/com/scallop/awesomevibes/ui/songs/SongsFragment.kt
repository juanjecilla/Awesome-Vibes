package com.scallop.awesomevibes.ui.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.common.BaseFragment
import com.scallop.awesomevibes.databinding.FragmentSongsBinding
import com.scallop.awesomevibes.entities.MusicVideo
import com.scallop.awesomevibes.entities.Song
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.ui.commons.EndlessRecyclerViewScrollListener
import com.scallop.awesomevibes.ui.commons.Utils
import com.scallop.awesomevibes.ui.commons.visible
import com.scallop.awesomevibes.ui.options.OptionsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SongsFragment : BaseFragment(), OnSongItemInteractor {

    private val mViewModel: SongsViewModel by viewModel {
        parametersOf(arguments?.let {
            val passedArguments = SongsFragmentArgs.fromBundle(it)
            passedArguments.searchAlbum
        }, arguments?.let {
            val passedArguments = SongsFragmentArgs.fromBundle(it)
            passedArguments.searchAlbumId
        })
    }
    private var mBinding: FragmentSongsBinding? = null

    private lateinit var mAdapter: SongsAdapter
    private lateinit var mLayoutManager: GridLayoutManager
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

        setFragmentResultListener(OptionsFragment.SELECTED_ACTION) { _, bundle ->
            when (bundle["data"]) {
                OptionsFragment.PLAY_ACTION -> mSelectedSong?.let { mViewModel.playSong(it.previewUrl) }
                OptionsFragment.SHARE_ACTION -> Utils.shareSong(
                    context,
                    mSelectedSong?.trackViewUrl
                )
                OptionsFragment.SEARCH_VIDEO_ACTION -> mSelectedSong?.let {
                    mViewModel.getMusicVideo(
                        it.trackName,
                        it.trackId.toLong()
                    )
                }
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
                        mViewModel.getSongs(page)
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
                    it.data?.let { it1 -> updateList(it1) }
                }
                Status.ERROR -> {
                }
            }
        })

        mViewModel.video.observe(viewLifecycleOwner, {
            when (it.responseType) {
                Status.LOADING -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, true) }
                }
                Status.SUCCESSFUL -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }
                    it.data?.let { it1 -> showMusicVideo(it1) }
                }
                Status.ERROR -> {
                    showVideoError()
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
        val action = SongsFragmentDirections.showOptions()
        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    private fun updateList(items: List<Song>) {
        mAdapter.submitList(items)

        if (mAdapter.itemCount == 0) {
            mBinding?.emptyLabel?.visible(true)
            mBinding?.songList?.visible(false)
        } else {
            mBinding?.emptyLabel?.visible(false)
            mBinding?.songList?.visible(true)
        }
    }

    private fun showMusicVideo(musicVideo: MusicVideo) {
        val action = SongsFragmentDirections.showVideo()
        action.mediaUrl = musicVideo.previewUrl
        action.mediaName = musicVideo.trackName
        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    private fun showVideoError() {
        mBinding?.root?.let {
            Snackbar.make(it, R.string.video_not_found, Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }

    override fun saveSong(song: Song) {
        mViewModel.saveSong(song)
    }
}