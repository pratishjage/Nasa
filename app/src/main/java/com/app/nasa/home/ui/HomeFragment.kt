package com.app.nasa.home.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.app.nasa.databinding.HomeFragmentBinding
import com.app.nasa.home.HomeRepo
import com.app.nasa.home.HomeVMF
import com.app.nasa.home.HomeViewModel

class HomeFragment : Fragment() {

  private var _binding: HomeFragmentBinding? = null

  private val binding get() = _binding!!

  companion object {
    fun newInstance() = HomeFragment()
  }

  private val viewModel by activityViewModels<HomeViewModel> {
    HomeVMF(
      HomeRepo(requireActivity().application)
    )
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = HomeFragmentBinding.inflate(inflater, container, false)
    return this.binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}