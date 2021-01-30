package com.scallop.awesomevibes.ui.commons

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment

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
        }
    } else {
        this.visibility = View.GONE
    }
}

fun EditText.on(actionId: Int, func: () -> Unit) {
    setOnEditorActionListener { _, receivedActionId, _ ->

        if (actionId == receivedActionId) {
            func()
        }

        true
    }
}

fun Fragment.hideKeyboard() {
    val inputManager: InputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        activity?.currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}