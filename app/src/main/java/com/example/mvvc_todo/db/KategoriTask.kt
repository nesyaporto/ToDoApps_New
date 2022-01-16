package com.example.mvvc_todo.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class KategoriTask (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var judul: String,
        var kategori:Int,
        var waktu: Long
    ) : Parcelable