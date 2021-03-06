package com.app.nasa.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.nasa.home.model.NasaDataModel
import com.beust.klaxon.Klaxon

class HomeViewModel(private val repo: HomeRepo) : ViewModel() {

  lateinit var images: MutableLiveData<List<NasaDataModel>>

  fun getAllImages(): LiveData<List<NasaDataModel>> {
    if (!::images.isInitialized) {
      images = MutableLiveData()
      val data = repo.readAssetFile("data.json")
      images.value = Klaxon().parseArray(data) ?: emptyList()
    }
    return images
  }
}