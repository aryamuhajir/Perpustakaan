package com.example.perpustakaan.room.user

class UserRepository (private val dao : UserDao) {

    fun registerDao(user : User){
        dao.register(user)
    }
    fun cekLoginRepo(user : String, password : String) : Int{
        return dao.cekLogin(user, password)
    }
}