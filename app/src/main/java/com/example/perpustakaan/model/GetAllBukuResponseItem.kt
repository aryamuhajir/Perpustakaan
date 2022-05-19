package com.example.perpustakaan.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetAllBukuResponseItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("judul")
    val judul: String,
    @SerializedName("penerbit")
    val penerbit: String,
    @SerializedName("penulis")
    val penulis: String,
    @SerializedName("sampul")
    val sampul: String,
    @SerializedName("sinopsis")
    val sinopsis: String,
    @SerializedName("tanggalPinjam")
    val tanggalPinjam: Int?,
    @SerializedName("tanggalRilis")
    val tanggalRilis: String
) : Parcelable