package com.example.perpustakaan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.example.perpustakaan.R
import com.example.perpustakaan.datastore.UserManager
import com.example.perpustakaan.model.GetAllBukuResponseItem
import com.example.perpustakaan.room.peminjaman.Peminjaman
import com.example.perpustakaan.room.peminjaman.PeminjamanDatabase
import com.example.perpustakaan.room.user.UserDatabase
import com.example.perpustakaan.viewmodel.ViewModelPinjam
import com.example.perpustakaan.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        val status = intent.getStringExtra("status")
        if (status != "home"){
            btnPinjam.text = "KEMBALIKAN"
        }

        val idBuku = detailBuku?.id
        val judul = detailBuku?.judul
        val penerbit = detailBuku?.penerbit
        val penulis = detailBuku?.penulis
        val sinopsis = detailBuku?.sinopsis
        val tanggalRilis = detailBuku?.tanggalRilis
        val sampul = detailBuku?.sampul


        txtJudul.text = judul
        txtPenerbit.text = penerbit
        txtPenulis.text = penulis
        txtSinopsis.text = sinopsis
        txtTanggal.text = tanggalRilis

        Glide.with(applicationContext).load(detailBuku?.sampul).into(imageBuku1)

        btnPinjam.setOnClickListener {
            if (btnPinjam.text.equals("PINJAM")){
                userManager.userNAME.asLiveData().observe(this){
                    var username = it.toString()
                    if (txtLangganan.text.equals("premium")){
                        GlobalScope.launch {
                            viewModel.pinjamLive(Peminjaman(null, username, detailBuku?.id!!.toInt(), "PREMIUM", judul!!, penerbit!!, penulis!!, sampul!! , sinopsis!!, tanggalRilis!!))
                            runOnUiThread {
                                Toast.makeText(this@DetailActivity, "Berhasil meminjam buku premium", Toast.LENGTH_LONG).show()
                            }
                        }
                    }else{
                        GlobalScope.launch {
                            viewModel.pinjamLive(Peminjaman(null, username, detailBuku?.id!!.toInt(), detailBuku?.tanggalPinjam.toString(),  judul!!, penerbit!!, penulis!!, sampul!! , sinopsis!!, tanggalRilis!!))
                            runOnUiThread {
                                Toast.makeText(this@DetailActivity, "Berhasil meminjam buku gratis", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                }
            }else{
                userManager.userNAME.asLiveData().observe(this) {
                    var username = it.toString()
                    GlobalScope.launch {
                        viewModel.kembaliLive(idBuku!!.toInt(), username)
                        runOnUiThread {
                            Toast.makeText(this@DetailActivity, "Berhasil mengembalikan buku", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }



        }
    }
}