package com.example.perpustakaan.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perpustakaan.R
import com.example.perpustakaan.adapter.RvAdapter
import com.example.perpustakaan.datastore.UserManager
import com.example.perpustakaan.viewmodel.ViewModelBuku
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var bookAdapter : RvAdapter

    private lateinit var userManager : UserManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        userManager = UserManager(this)

        userManager.userNAME.asLiveData().observe(this){
            txtNama.text = it
        }

        getDataBuku()

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataBuku(){
        val viewModel = ViewModelProvider(this).get(ViewModelBuku::class.java)
        viewModel.getLiveFilmObserver().observe(this) { it ->
            if (it != null){
                rv_item.layoutManager = LinearLayoutManager(this)
                bookAdapter = RvAdapter {
                    val pindah = Intent(this@HomeActivity, DetailActivity::class.java)
                    pindah.putExtra("detailbuku", it)
                    pindah.putExtra("status", "home")
                    startActivity(pindah)
                }
                rv_item.adapter = bookAdapter
                bookAdapter.setDataFilm(it)
                bookAdapter.notifyDataSetChanged()
            }
        }
        viewModel.makeApiBuku()
    }
}