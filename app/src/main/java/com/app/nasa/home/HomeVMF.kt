package com.app.nasa.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeVMF(private val repo: HomeRepo) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return HomeViewModel(repo = repo) as T
  }
}