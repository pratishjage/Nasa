package com.app.nasa.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.nasa.R
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

  private val adapter by lazy {
    NasaAdapter { clickedPosition ->
      viewModel.setCurrentImage(clickedPosition)
      viewModel.onAdapterItemClicked()
    }
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
    binding.rvHome.layoutManager = GridLayoutManager(context, 2)
    binding.rvHome.adapter = adapter
    viewModel.getAllImages().observe(viewLifecycleOwner, Observer {
      adapter.swapData(it)
    })
    viewModel.navigateToImageDetail.observe(viewLifecycleOwner, Observer { navigate ->
      if (navigate){
        findNavController().navigate(R.id.action_homeFragment_to_imageDetailFragment)
        viewModel.navigatedToImageDetailScreen()
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}