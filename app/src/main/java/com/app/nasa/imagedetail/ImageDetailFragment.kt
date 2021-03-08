package com.app.nasa.imagedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.nasa.R
import com.app.nasa.databinding.FragmentImageDetailBinding
import com.app.nasa.databinding.HomeFragmentBinding
import com.app.nasa.home.HomeRepo
import com.app.nasa.home.HomeVMF
import com.app.nasa.home.HomeViewModel
import com.app.nasa.imagedetail.adapter.ImageDetailAdapter

class ImageDetailFragment : Fragment() {
  private var _binding: FragmentImageDetailBinding? = null

  private val binding get() = _binding!!

  companion object {
    fun newInstance() =
      ImageDetailFragment()
  }

  private val viewModel by activityViewModels<HomeViewModel> {
    HomeVMF(
      HomeRepo(requireActivity().application)
    )
  }

  private lateinit var adapter: ImageDetailAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentImageDetailBinding.inflate(inflater, container, false)
    return this.binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getAllImages().observe(viewLifecycleOwner, Observer {
      adapter = ImageDetailAdapter(this, it)
      binding.vp.adapter = adapter
    })
    viewModel.currentImage.observe(viewLifecycleOwner, Observer {
      if (binding.vp.adapter != null) {
        binding.vp.setCurrentItem(it, false)
      }
    })
    binding.btnBack.setOnClickListener { findNavController().popBackStack() }
  }
}