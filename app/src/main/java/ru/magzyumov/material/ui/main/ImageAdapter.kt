package ru.magzyumov.material.ui.main


import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.magzyumov.material.R
import ru.magzyumov.material.databinding.ItemImageBinding
import java.io.File


class ImageAdapter(
        images: List<String>,
        private val interaction: Interaction
): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var imageList: MutableList<String> = mutableListOf()

    init {
        imageList.addAll(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view, interaction)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    fun swap(images: List<String>) {
        val diffCallback = DiffCallback(imageList, images)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        imageList.clear()
        imageList.addAll(images)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
            view: View,
            private val interaction: Interaction
    ): RecyclerView.ViewHolder(view) {
        private val binding = ItemImageBinding.bind(view)

        fun bind(image: String) {

            loadImage(binding.imageViewMain, image)

            binding.buttonShare.setOnClickListener{
                interaction.onShareSelected(image)
            }

            binding.buttonLike.setOnClickListener{
                interaction.onLikeSelected(adapterPosition, image)
            }
        }
    }

    inner class DiffCallback(
        private val oldList: List<String>,
        private val newList: List<String>
    ): DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    interface Interaction {
        fun onShareSelected(item: String)
        fun onLikeSelected(position: Int, item: String)
    }

    private fun loadImage(view: ImageView, image: String) {
        Glide.with(view.context)
                .load(Uri.parse(image))
                .centerCrop()
                .fitCenter()
                .into(view)
    }
}
