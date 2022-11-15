package com.hampson.asta.util

import android.os.Parcelable
import com.app.imagepickerlibrary.model.PickExtension
import com.app.imagepickerlibrary.model.PickerType
import kotlinx.parcelize.Parcelize

@Parcelize
data class PickerOptions(
    val pickerType: PickerType,
    val showCountInToolBar: Boolean,
    val showFolders: Boolean,
    val allowMultipleSelection: Boolean,
    val maxPickCount: Int,
    val maxPickSizeMB: Float,
    val pickExtension: PickExtension,
    val showCameraIconInGallery: Boolean,
    val isDoneIcon: Boolean,
    val openCropOptions: Boolean,
    val openSystemPicker: Boolean,
    val compressImage: Boolean
) : Parcelable {
    companion object {
        fun default(): PickerOptions {
            return PickerOptions(
                pickerType = PickerType.GALLERY,
                showCountInToolBar = true,
                showFolders = true,
                allowMultipleSelection = true,
                maxPickCount = 10,
                maxPickSizeMB = 2.5f,
                pickExtension = PickExtension.ALL,
                showCameraIconInGallery = false,
                isDoneIcon = true,
                openCropOptions = false,
                openSystemPicker = false,
                compressImage = false
            )
        }
    }
}