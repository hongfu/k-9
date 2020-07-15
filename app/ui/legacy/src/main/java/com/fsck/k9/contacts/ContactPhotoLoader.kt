package com.fsck.k9.contacts

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.fsck.k9.helper.Contacts
import timber.log.Timber

internal class ContactPhotoLoader(private val context: Context, private val contacts: Contacts) {
    fun loadContactPhoto(emailAddress: String): Bitmap? {
        val photoUri = contacts.getPhotoUri(emailAddress) ?: return null
        return try {
            context.contentResolver.openInputStream(photoUri).use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            Timber.e(e, "Couldn't load contact photo: $photoUri")
            null
        }
    }
}
