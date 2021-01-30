package com.scallop.awesomevibes.ui.commons

import android.content.Context
import android.content.Intent
import com.scallop.awesomevibes.R

object Utils {
    fun shareSong(context: Context?, url: String?) {
        context?.let {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, it.getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            shareIntent.type = "text/plain"

            it.startActivity(
                Intent.createChooser(
                    shareIntent,
                    it.getString(R.string.share_label)
                )
            )
        }
    }
}