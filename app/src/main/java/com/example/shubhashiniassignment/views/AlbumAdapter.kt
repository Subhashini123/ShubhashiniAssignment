package com.example.shubhashiniassignment.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shubhashiniassignment.R
import com.example.shubhashiniassignment.databinding.AlbumCardBinding
import com.example.shubhashiniassignment.model.AlbumModel
import com.squareup.picasso.Picasso

class AlbumAdapter (
    private var listener: RecordSelectListener
    ) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){

        var recordSelectListener: RecordSelectListener = listener
    companion object {
        var albums: MutableList<AlbumModel.AlbumModelItem> = mutableListOf()
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
            val v = AlbumCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AlbumViewHolder(v)
        }

        override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
            holder.bindItem(albums[position])
            if (position == albums.size - 1) {
                listener.loadMoreDataToRecyclerView(position+1)
            }
        }

        override fun getItemCount(): Int {
            return albums.size
        }
    fun appendList(lst: List<AlbumModel.AlbumModelItem>?) {
        if (lst.isNullOrEmpty()) {
            return
        } else {
            albums.addAll(lst)
        }

    }
        inner class AlbumViewHolder(private val itemBinding: AlbumCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
            fun bindItem(model: AlbumModel.AlbumModelItem) {
                itemBinding.txtAlbumTitle.text = model.title
                Picasso.get()
                    .load(model.thumbnailUrl)
                    .fit()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(itemBinding.img)

                itemBinding.root.setOnClickListener {
                    recordSelectListener.onRecordSelect(model)
                }
            }
        }
    interface RecordSelectListener {
        fun onRecordSelect(albumModel: AlbumModel.AlbumModelItem)
        fun loadMoreDataToRecyclerView(indexPosition: Int)
    }
}