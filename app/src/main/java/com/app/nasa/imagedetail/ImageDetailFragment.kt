package com.app.nasa.imagedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.nasa.R
import com.app.nasa.databinding.FragmentImageDetailBinding
import com.app.nasa.databinding.HomeFragmentBinding

class ImageDetailFragment : Fragment() {
  private var _binding: FragmentImageDetailBinding? = null

  private val binding get() = _binding!!

  companion object {
    fun newInstance() =
      ImageDetailFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentImageDetailBinding.inflate(inflater, container, false)
    return this.binding.root
  }
}