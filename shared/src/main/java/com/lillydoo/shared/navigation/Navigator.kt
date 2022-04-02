package com.lillydoo.shared.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.lillydoo.shared.R
import com.lillydoo.shared.navigation.NavigationType.JOKES

class Navigator(private val context: Context) {
    fun navigate(type: NavigationType, arg: String) {
        when (type) {
            JOKES -> {
                val builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.app_name)
                builder.setMessage(arg)
                builder.setPositiveButton(android.R.string.ok, null)
                builder.show()
            }
        }
    }
}