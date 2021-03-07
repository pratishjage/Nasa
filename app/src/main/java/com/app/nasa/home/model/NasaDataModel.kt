package com.app.nasa.home.model

import android.os.Parcelable
import com.beust.klaxon.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NasaDataModel(
  @Json(name = "copyright")
  val copyright: String? = null,
  @Json(name = "date")
  val date: String?,
  @Json(name = "explanation")
  val explanation: String?,
  @Json(name = "hdurl")
  val hdUrl: String?,
  @Json(name = "media_type")
  val media_type: String?,
  @Json(name = "service_version")
  val service_version: String?,
  @Json(name = "title")
  val title: String?,
  @Json(name = "url")
  val url: String?
):Parcelable