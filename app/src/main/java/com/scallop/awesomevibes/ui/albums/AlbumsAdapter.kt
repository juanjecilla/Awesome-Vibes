package com.scallop.awesomevibes.ui.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.ItemAlbumBinding
import com.scallop.awesomevibes.entities.Album
import com.scallop.awesomevibes.entities.Artist
import com.scallop.awesomevibes.ui.commons.OnItemClick

class AlbumsAdapter(private val mListener: OnItemClick<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.ArtistsListViewHolder>() {

    private var mData = mutableListOf<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return ArtistsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ArtistsListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    fun updateList(list: List<Album>) {
        if (list.isNotEmpty()) {
            val prevCount = itemCount
            mData.addAll(list)
            notifyItemRangeChanged(prevCount, list.size)
        }
    }

    inner class ArtistsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mItem: Album
        private val mBinding = ItemAlbumBinding.bind(itemView)

        init {
            itemView.setOnClickListener { mListener.onItemClicked(mItem) }
        }

        fun bind(item: Album) {
            mItem = item

            with(mBinding) {
                albumImage.load(item.artworkUrl100)
                albumName.text = item.collectionName
                albumRelease.text = item.releaseDate
            }
        }
    }
}