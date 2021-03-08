package com.app.nasa.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.nasa.home.model.NasaDataModel
import com.beust.klaxon.Klaxon
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class HomeViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  private val repo = mock<HomeRepo>()

  private val dataObs = mock<Observer<List<NasaDataModel>>>()
  private lateinit var viewModel: HomeViewModel

  @Before
  fun setUp() {
    viewModel = HomeViewModel(repo)
  }

  @Test
  fun empty_list_Of_Images() {
    whenever(repo.readAssetFile("data.json")).doReturn("[]")
    viewModel.getAllImages()
    viewModel.images.observeForever(dataObs)
    verify(dataObs).onChanged(emptyList())
  }

  @Test
  fun valid_list_of_images() {
    whenever(repo.readAssetFile("data.json")).doReturn(String(Files.readAllBytes(Paths.get("src/main/assets/data.json"))))
    viewModel.getAllImages()
    viewModel.images.observeForever(dataObs)
    verify(dataObs).onChanged(getSuccessData())
  }

  private fun getSuccessData(): List<NasaDataModel>? {
    val file = File("src/main/assets/data.json")
    return Klaxon().parseArray<NasaDataModel>(file)
  }
}