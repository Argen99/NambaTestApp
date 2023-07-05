package com.example.nambatestapp.app.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.nambatestapp.app.core.extensions.loadImage
import com.example.nambatestapp.app.core.extensions.toPx
import com.example.nambatestapp.databinding.PictureItemBinding
import com.example.nambatestapp.domain.model.PicturesModel

class PicturesPagingAdapter(
    private val context: Context,
    private val onItemClick: (image: String?, imageView: ImageView) -> Unit
) : PagingDataAdapter<PicturesModel, PicturesPagingAdapter.PicturesViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        return PicturesViewHolder(
            PictureItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.onBind(item)
        }
    }

    inner class PicturesViewHolder(private val binding: PictureItemBinding) :
        ViewHolder(binding.root) {

        fun onBind(item: PicturesModel) {
            binding.itemImage.transitionName = item.id.toString()
            binding.itemImage.layoutParams.height = item.previewHeight.toPx(context)
            binding.itemImage.loadImage(item.webFormatURL)
        }

        init {
            binding.root.setOnClickListener {
                onItemClick(
                    getItem(absoluteAdapterPosition)?.webFormatURL,
                    binding.itemImage
                )
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<PicturesModel>() {
            override fun areItemsTheSame(oldItem: PicturesModel, newItem: PicturesModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PicturesModel,
                newItem: PicturesModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}