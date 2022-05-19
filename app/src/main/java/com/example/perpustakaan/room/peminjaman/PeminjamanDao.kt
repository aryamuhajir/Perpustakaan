package com.example.perpustakaan.room.peminjaman

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeminjamanDao {

    @Insert
    fun pinjam(peminjam : Peminjaman) : Long

    @Query("SELECT * FROM Peminjaman WHERE username = :username")
    fun getPeminjaman(username : String) : List<Peminjaman>


}