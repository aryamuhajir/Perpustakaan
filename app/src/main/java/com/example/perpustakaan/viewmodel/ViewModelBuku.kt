package com.example.perpustakaan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perpustakaan.model.GetAllBukuResponseItem
import com.example.perpustakaan.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelBuku () : ViewModel() {


    lateinit var liveDataBuku: MutableLiveData<List<GetAllBukuResponseItem>>

    init {

        liveDataBuku = MutableLiveData()
    }

    fun getLiveFilmObserver(): MutableLiveData<List<GetAllBukuResponseItem>> {
        return liveDataBuku
    }


    fun makeApiBuku() {
        APIClient.instance.getAllBuku()
            .enqueue(object : Callback<List<GetAllBukuResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllBukuResponseItem>>,
                    response: Response<List<GetAllBukuResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        liveDataBuku.postValue(response.body())
                    } else {
                    }

                }

                override fun onFailure(call: Call<List<GetAllBukuResponseItem>>, t: Throwable) {
                }

            })
    }
}