package com.hampson.asta.util

import android.content.Context
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hampson.asta.R

fun showTwoButtonDialog(
    context: Context,
    title: String? = null,
    message: String,
    onPositiveButton: () -> Unit?,
    onNegativeButton: () -> Unit?
) {
    MaterialAlertDialogBuilder(
        context,
        R.style.ThemeOverlay_App_MaterialAlertDialog
    ).setMessage(message)
        .setTitle(title ?: context.getString(R.string.guide))
        .setNegativeButton(context.getString(R.string.cancel)) { _, _ ->
            onNegativeButton.invoke()
        }
        .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
            onPositiveButton.invoke()
        }
        .show()
}

fun View.showSnackBar(
    message: String
) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

