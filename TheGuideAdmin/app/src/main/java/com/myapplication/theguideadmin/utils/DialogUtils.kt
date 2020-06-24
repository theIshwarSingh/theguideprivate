package com.myapplication.theguideadmin.utils

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogUtils {
    fun showErrorDialog(
        title: String, message: String, buttonText: String,
        listener: DialogInterface.OnClickListener, context: Context
    ) {
        val dialog = MaterialAlertDialogBuilder(context)
        dialog.setTitle(title)
        dialog.setCancelable(true)
        dialog.setMessage(message)
        dialog.setPositiveButton(buttonText, listener)
        dialog.show()
    }
}