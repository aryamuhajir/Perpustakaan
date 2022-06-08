package com.example.perpustakaan.room.peminjaman

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.perpustakaan.room.user.User

@Database(entities = [Peminjaman::class], version = 1)
abstract class PeminjamanDatabase : RoomDatabase() {

    abstract fun peminjamanDao() : PeminjamanDao

    companion object{
        private var INSTANCE : PeminjamanDatabase? = null
        fun getInstance(context : Context): PeminjamanDatabase? {
            if (INSTANCE == null){
                synchronized(User::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PeminjamanDatabase::class.java,"Peminjaman3.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        //       fun destroyInstance(){
        //          INSTANCE = null
        //      }
    }
}