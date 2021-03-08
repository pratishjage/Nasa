package com.app.nasa.home

import android.app.Application
import com.app.nasa.utils.FileUtils

class HomeRepo(private val application: Application) {

  fun readAssetFile(fileName: String): String {
   return FileUtils.readAssetFile(application, fileName)
  }
}