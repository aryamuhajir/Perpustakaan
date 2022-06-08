package com.example.perpustakaan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.perpustakaan.R
import com.example.perpustakaan.room.peminjaman.Peminjaman
import kotlinx.android.synthetic.main.item_adapter_buku.view.cardFilm
import kotlinx.android.synthetic.main.item_adapter_buku.view.imageBuku
import kotlinx.android.synthetic.main.item_adapter_buku.view.judulBuku
import kotlinx.android.synthetic.main.item_adapter_buku.view.penulisBuku
import kotlinx.android.synthetic.main.item_adapter_buku.view.tanggalRilisBuku
import kotlinx.android.synthetic.main.item_adapter_buku_2.view.*

class PinjamAdapter(private var onClick : (Peminjaman)->Unit) : RecyclerView.Adapter<PinjamAdapter.ViewHolder>() {

    private var dataBuku : List<Peminjaman>? = null

    fun setDataFilm(buku : List<Peminjaman>){
        this.dataBuku = buku
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_buku_2, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.judulBuku.text = dataBuku!![position].judul
        holder.itemView.tanggalRilisBuku.text = dataBuku!![position].tanggalRilis
        holder.itemView.penulisBuku.text = dataBuku!![position].penulis
        holder.itemView.deadlineBuku.text = dataBuku!![position].deadline

        Glide.with(holder.itemView.context).load(dataBuku!![position].sampul).apply(RequestOptions().override(120, 120)).into(holder.itemView.imageBuku)



        holder.itemView.cardFilm.setOnClickListener{
            onClick(dataBuku!![position])
        }

    }

    override fun getItemCount(): Int {
        return if (dataBuku == null){
            0
        }else{
            dataBuku!!.size

        }
    }
}