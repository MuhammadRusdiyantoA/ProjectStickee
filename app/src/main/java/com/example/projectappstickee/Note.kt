package com.example.projectappstickee

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val noteId: String,
    val judul: String,
    val isi: String,
) : Parcelable
