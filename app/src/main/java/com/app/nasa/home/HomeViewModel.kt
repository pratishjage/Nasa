package com.app.nasa.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.nasa.home.model.NasaDataModel
import com.beust.klaxon.Klaxon

class HomeViewModel(private val repo: HomeRepo) : ViewModel() {

  lateinit var images: MutableLiveData<List<NasaDataModel>>

  private val _navigateToImageDetail = MutableLiveData<Boolean>()
  val navigateToImageDetail
    get() = _navigateToImageDetail

  private val _currentImage = MutableLiveData<Int>()
  val currentImage
    get() = _currentImage

  fun getAllImages(): LiveData<List<NasaDataModel>> {
    if (!::images.isInitialized) {
      images = MutableLiveData()
      val data = repo.readAssetFile("data.json")
      images.value = Klaxon().parseArray(data) ?: emptyList()
    }
    return images
  }

  fun setCurrentImage(clickedPosition: Int) {
    currentImage.value = clickedPosition
  }

  fun onAdapterItemClicked() {
    _navigateToImageDetail.value = true
  }

  fun navigatedToImageDetailScreen() {
    _navigateToImageDetail.value = false
  }
}