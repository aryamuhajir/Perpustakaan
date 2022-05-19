package com.example.perpustakaan.room.peminjaman

import com.example.perpustakaan.room.user.UserDao

class PeminjamanRepository(private val dao : PeminjamanDao) {

    suspend fun pinjamRepo(pinjam : Peminjaman) {
        dao.pinjam(pinjam)
    }

    suspend fun getPinjamRepo(username : String) : List<Peminjaman>{
        return dao.getPeminjaman(username)
    }
}