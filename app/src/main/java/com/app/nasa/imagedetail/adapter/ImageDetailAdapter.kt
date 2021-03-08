package com.app.nasa.imagedetail.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.nasa.home.model.NasaDataModel

class ImageDetailAdapter(fragment: Fragment,private val data:List<NasaDataModel>):FragmentStateAdapter(fragment) {
  override fun getItemCount(): Int {
    return data.size
  }

  override fun createFragment(position: Int): Fragment {
    return ImageDetailItemFragment.newInstance(data[position])
  }
}