package com.app.nasa.imagedetail.adapter

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import club.cred.synth.appearances.NeuPlatformAppearance
import club.cred.synth.drawables.ElevatedDrawable
import club.cred.synth.drawables.PitDrawable
import coil.load
import com.app.nasa.R
import com.app.nasa.databinding.FragmentImageDetailBinding
import com.app.nasa.databinding.FragmentImageDetailItemBinding
import com.app.nasa.home.model.NasaDataModel

private const val IMAGE_DATA = "image_data"

class ImageDetailItemFragment : Fragment() {
  private var imageData: NasaDataModel? = null

  private var _binding: FragmentImageDetailItemBinding? = null

  private val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      imageData = it.getParcelable(IMAGE_DATA)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentImageDetailItemBinding.inflate(inflater, container, false)
    return this.binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    imageData?.let { data ->
      if (VERSION.SDK_INT >= VERSION_CODES.O) {
        binding.flHdImage.background =
          ElevatedDrawable(
            NeuPlatformAppearance(view.context.getColor(R.color.dark_grey)), 16f, 16f
          )
      }
      binding.ivHdImage.load(data.hdUrl) {
        placeholder(R.drawable.ic_placeholder)
      }
      binding.flDescription.background = PitDrawable()
      binding.tvDescription.text = data.explanation
      binding.tvTitle.text = data.title
    }
  }

  companion object {
    @JvmStatic fun newInstance(
      data: NasaDataModel
    ) =
      ImageDetailItemFragment().apply {
        arguments = Bundle().apply {
          putParcelable(IMAGE_DATA, data)
        }
      }
  }
}