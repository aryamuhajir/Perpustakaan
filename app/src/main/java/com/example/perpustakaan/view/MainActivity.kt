package com.example.perpustakaan.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.perpustakaan.R
import com.example.perpustakaan.datastore.UserManager
import com.example.perpustakaan.room.user.UserDatabase
import com.example.perpustakaan.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userManager: UserManager

    private var userDb : UserDatabase? = null
    private lateinit var viewModel : ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)

        userDb = UserDatabase.getInstance(this)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelUser::class.java)

        txtBlmPunyaAkun.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val user = editUsername.text.toString()
            val password = editPassword.text.toString()
            if (user.isNotBlank() && password.isNotBlank()){
                viewModel.cekData.observe(this) {
                    if (it != 0) {
                        Toast.makeText(this, "Berhasil Login", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        loginDataStore(user, password)
                        finish()
                    } else {
                        Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                viewModel.loginLive(user, password)
            }else{
                Toast.makeText(this, "Username atau Password masih kosong!", Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun loginDataStore(username : String, password : String){
        GlobalScope.launch {
            userManager.login(username, password)
            userManager.setStatus("yes")
        }
    }
}