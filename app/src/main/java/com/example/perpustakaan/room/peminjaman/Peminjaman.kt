package com.example.perpustakaan.room.peminjaman

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Peminjaman(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo(name = "username")
    var username : String,
    @ColumnInfo(name = "id_buku")
    var idBuku : Int,
    @ColumnInfo(name = "deadline")
    var deadline : Int?
) : Parcelable
