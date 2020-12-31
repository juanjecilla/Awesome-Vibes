package com.scallop.awesomevibes.ui.commons

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.visible(visible: Boolean, animate: Boolean = true) {
    if (visible) {
        if (animate) {
            animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    visibility = View.VISIBLE
                }
            })
        } else {
            visibility = View.VISIBLE
        }    } else {
        this.visibility = View.GONE
    }
}