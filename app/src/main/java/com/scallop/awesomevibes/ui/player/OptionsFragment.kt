package com.scallop.awesomevibes.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.FragmentOptionsBinding

class OptionsFragment : BottomSheetDialogFragment() {

    private var mBinding: FragmentOptionsBinding? = null

    private lateinit var mMediaUrl: String
    private var mIsVideo: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentOptionsBinding.bind(view)

        arguments?.let {
            val passedArguments = OptionsFragmentArgs.fromBundle(it)
            mMediaUrl = passedArguments.mediaUrl
            mIsVideo = passedArguments.isVideo
        }


        mBinding?.let {
            with(it) {
                share.setOnClickListener { sendResult(SHARE_ACTION) }
                play.setOnClickListener { sendResult(PLAY_ACTION) }
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun sendResult(action: String) {
        setFragmentResult(SELECTED_ACTION, bundleOf("data" to action))
        findNavController().navigateUp()
    }

    companion object {
        const val SELECTED_ACTION = "Options_selected_action"

        const val SHARE_ACTION = "action_share"
        const val PLAY_ACTION = "action_play"
    }
}