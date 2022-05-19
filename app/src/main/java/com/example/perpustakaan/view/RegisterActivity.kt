package com.example.perpustakaan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.perpustakaan.R
import com.example.perpustakaan.room.user.User
import com.example.perpustakaan.room.user.UserDatabase
import com.example.perpustakaan.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    var userDb : UserDatabase? = null
    lateinit var viewModel : ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        userDb = UserDatabase.getInstance(this)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelUser::class.java)


        btnDaftar.setOnClickListener {
            val username = editUsername1.text.toString()
            val password = editPassword1.text.toString()
            val password1 = editPassword2.text.toString()
            val email = editEmail1.text.toString()
            if (password.equals(password1)){
                GlobalScope.launch {
                    viewModel.registerLive(User(null, username, email, password))
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Berhasil membuat akun", Toast.LENGTH_LONG).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                }
            }
        }



    }
}