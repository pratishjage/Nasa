package com.app.nasa.utils

import android.app.Application

object FileUtils {
  fun readAssetFile(
    application: Application,
    fileName: String
  ): String {
    val bufferReader = application.assets.open(fileName).bufferedReader()
    return bufferReader.use {
      it.readText()
    }
  }
}