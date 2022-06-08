@file:Suppress("RedundantExplicitType")

package com.example.perpustakaan.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perpustakaan.R
import com.example.perpustakaan.adapter.PinjamAdapter
import com.example.perpustakaan.datastore.UserManager
import com.example.perpustakaan.model.GetAllBukuResponseItem
import com.example.perpustakaan.room.peminjaman.PeminjamanDatabase
import com.example.perpustakaan.viewmodel.ViewModelPinjam
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private var pinjamDb : PeminjamanDatabase? = null
    private lateinit var viewModel : ViewModelPinjam
    private lateinit var userManager : UserManager
    private lateinit var bookAdapter : PinjamAdapter
    var username = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        pinjamDb = PeminjamanDatabase.getInstance(this)
        userManager = UserManager(this)

        userManager.userNAME.asLiveData().observe(this){
            username = it
            getAllPinjaman(username)

        }
        btnLogout.setOnClickListener {
            GlobalScope.launch {
                userManager.logout()
                userManager.setStatus("no")
            }
            startActivity(Intent(this, SplashActivity::class.java))

        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllPinjaman(username : String){
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelPinjam::class.java)


            viewModel.getLiveBukuObserver().observe(this@ProfileActivity){ it ->
                if (it.isNotEmpty()){
                    rv_list.layoutManager = LinearLayoutManager(this@ProfileActivity)
                    bookAdapter = PinjamAdapter {
                        val pindah = Intent(this@ProfileActivity, DetailActivity::class.java)
                        val detailBuku : GetAllBukuResponseItem = GetAllBukuResponseItem("asdasd", it.idBuku.toString(),it.judul, it.penerbit,it.penulis,it.sampul,
                        it.sinopsis, 0, it.tanggalRilis)
                        pindah.putExtra("detailbuku", detailBuku)
                        pindah.putExtra("status", "profile")

                        startActivity(pindah)
                    }
                    rv_list.adapter = bookAdapter
                    bookAdapter.setDataFilm(it)
                    bookAdapter.notifyDataSetChanged()
                    txtBelum.visibility = View.INVISIBLE

                }else{
                    txtBelum.visibility = View.VISIBLE
                }

            }
            viewModel.getPinjamLive(username)


    }

    override fun onResume() {
        super.onResume()
        pinjamDb = PeminjamanDatabase.getInstance(this)
        getAllPinjaman(username)
    }
}