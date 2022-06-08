package com.example.perpustakaan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.perpustakaan.R
import com.example.perpustakaan.model.GetAllBukuResponseItem
import kotlinx.android.synthetic.main.item_adapter_buku.view.*

class RvAdapter(private var onClick : (GetAllBukuResponseItem)->Unit) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var dataBuku : List<GetAllBukuResponseItem>? = null

    fun setDataFilm(buku : List<GetAllBukuResponseItem>){
        this.dataBuku = buku
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_buku, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.judulBuku.text = dataBuku!![position].judul
        holder.itemView.tanggalRilisBuku.text = dataBuku!![position].tanggalRilis
        holder.itemView.penulisBuku.text = dataBuku!![position].penulis
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