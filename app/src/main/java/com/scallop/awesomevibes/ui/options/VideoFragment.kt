package com.scallop.awesomevibes.ui.options

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.FragmentVideoBinding

class VideoFragment : BottomSheetDialogFragment() {

    private var mBinding: FragmentVideoBinding? = null

    private lateinit var mMediaUrl: String
    private lateinit var mMediaName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentVideoBinding.bind(view)

        arguments?.let {
            val passedArguments = VideoFragmentArgs.fromBundle(it)
            mMediaUrl = passedArguments.mediaUrl
            mMediaName = passedArguments.mediaName
        }

        mBinding?.let {
            with(it) {
                videoName.text = mMediaName

                videoView.setVideoURI(Uri.parse(mMediaUrl))
                videoView.start()
            }
        }
    }

    override fun onDestroyView() {
        mBinding?.videoView?.stopPlayback()
        mBinding = null
        super.onDestroyView()
    }

    companion object {
    }
}