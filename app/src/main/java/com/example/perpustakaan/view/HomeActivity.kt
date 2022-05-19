package com.example.perpustakaan.view

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
    lateinit var bookAdapter : RvAdapter

    lateinit var userManager : UserManager


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

    fun getDataBuku(){
        val viewModel = ViewModelProvider(this).get(ViewModelBuku::class.java)
        viewModel.getLiveFilmObserver().observe(this) {
            if (it != null){
                rv_item.layoutManager = LinearLayoutManager(this)
                bookAdapter = RvAdapter (){
                    val pindah = Intent(this@HomeActivity, DetailActivity::class.java)
                    pindah.putExtra("detailbuku", it)
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