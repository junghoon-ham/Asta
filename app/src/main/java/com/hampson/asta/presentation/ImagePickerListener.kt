package com.hampson.asta.presentation

import android.net.Uri

interface ImagePickerListener {
    fun onImagePick(uri: Uri?)
    fun onMultiImagePick(uris: List<Uri>?)
}