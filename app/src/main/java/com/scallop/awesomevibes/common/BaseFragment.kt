package com.scallop.awesomevibes.common

import androidx.fragment.app.Fragment
import com.scallop.awesomevibes.databinding.ProgressBarBinding
import com.scallop.awesomevibes.ui.commons.visible

open class BaseFragment: Fragment() {

    fun showProgressBar(binding: ProgressBarBinding, visible: Boolean){
        binding.root.visible(visible)
    }
}
