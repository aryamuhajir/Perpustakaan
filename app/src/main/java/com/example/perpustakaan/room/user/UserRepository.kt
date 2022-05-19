package com.example.perpustakaan.room.user

class UserRepository (private val dao : UserDao) {

    suspend fun registerDao(user : User){
        dao.register(user)
    }
    suspend fun cekLoginRepo(user : String, password : String) : Int{
        return dao.cekLogin(user, password)
    }
}