package com.example.perpustakaan.room.peminjaman

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PeminjamanDao {

    @Insert
    fun pinjam(peminjam : Peminjaman) : Long

}