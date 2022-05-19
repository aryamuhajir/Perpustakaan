package com.example.perpustakaan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.example.perpustakaan.R
import com.example.perpustakaan.datastore.UserManager
import com.example.perpustakaan.model.GetAllBukuResponseItem
import com.example.perpustakaan.room.peminjaman.PeminjamanDatabase
import com.example.perpustakaan.room.user.UserDatabase
import com.example.perpustakaan.viewmodel.ViewModelPinjam
import com.example.perpustakaan.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var pinjamDb : PeminjamanDatabase? = null
    lateinit var viewModel : ViewModelPinjam
    lateinit var userManager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        userManager = UserManager(this)
        pinjamDb = PeminjamanDatabase.getInstance(this)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelPinjam::class.java)


        val detailBuku = intent.getParcelableExtra<GetAllBukuResponseItem>("detailbuku")

        txtJudul.text = detailBuku?.judul
        txtPenerbit.text = detailBuku?.penerbit
        txtPenulis.text = detailBuku?.penulis
        txtSinopsis.text = detailBuku?.sinopsis
        txtTanggal.text = detailBuku?.tanggalRilis

        Glide.with(applicationContext).load(detailBuku?.sampul).into(imageBuku1)

        btnPinjam.setOnClickListener {
             userManager.userNAME.asLiveData().observe(this){
                 var username = it.toString()
            }

        }
    }
}