package com.app.nasa

import com.app.nasa.home.model.NasaDataModel
import com.beust.klaxon.Klaxon
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.json.JSONArray
import org.json.JSONException
import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class DataFileUnitTest {

  @Test
  fun data_file_isPresent() {
    val file = File("src/main/assets/data.json")
    assert(file.exists())
  }

  @Test
  fun data_file_is_validJSON() {
    val data = String(Files.readAllBytes(Paths.get("src/main/assets/data.json")))
    try {
      JSONArray(data)
      assert(true)
    } catch (e: JSONException) {
      assert(false)
    }
  }

  @Test
  fun data_file_is_mapped_to_nasa_model() {
    val file = File("src/main/assets/data.json")
    val model = Klaxon().parseArray<NasaDataModel>(file)
    assertThat(model, notNullValue())
  }
}