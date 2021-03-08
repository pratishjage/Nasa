package com.app.nasa.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import club.cred.synth.drawables.PitDrawable
import coil.load
import coil.transform.RoundedCornersTransformation
import com.app.nasa.R
import com.app.nasa.databinding.ItemNasaGridBinding
import com.app.nasa.home.model.NasaDataModel
import com.app.nasa.home.ui.NasaAdapter.ItemViewHolder

class NasaAdapter(private val onItemClicked: (Int) -> Unit) : ListAdapter<NasaDataModel, ItemViewHolder>(
  DiffCallback()
) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ItemViewHolder {
    return ItemViewHolder(ItemNasaGridBinding.inflate(LayoutInflater.from(parent.context)),onItemClicked)
  }

  override fun onBindViewHolder(
    holder: NasaAdapter.ItemViewHolder,
    position: Int
  ) {
    holder.bind(getItem(position))
  }

  class ItemViewHolder(private val binding: ItemNasaGridBinding,onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(
    binding.root
  ) {
    init {
      binding.root.setOnClickListener {
        onItemClicked(adapterPosition)
      }
    }
    fun bind(item: NasaDataModel) = with(binding) {
      binding.ivBanner.load(item.url) {
        placeholder(R.drawable.ic_placeholder)
        transformations(RoundedCornersTransformation(16f))
      }
      clContainer.background = PitDrawable()
    }
  }

  fun swapData(data: List<NasaDataModel>) {
    submitList(data)
  }
}

class DiffCallback : DiffUtil.ItemCallback<NasaDataModel>() {
  override fun areItemsTheSame(
    oldItem: NasaDataModel,
    newItem: NasaDataModel
  ): Boolean {
    return oldItem.url == newItem.url
  }

  override fun areContentsTheSame(
    oldItem: NasaDataModel,
    newItem: NasaDataModel
  ): Boolean {
    return oldItem == newItem
  }
}