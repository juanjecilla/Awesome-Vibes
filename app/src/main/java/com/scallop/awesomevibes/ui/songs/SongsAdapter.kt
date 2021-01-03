package com.scallop.awesomevibes.ui.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.ItemSongBinding
import com.scallop.awesomevibes.entities.Song

class SongsAdapter(private val mListener: OnSongItemInteractor) :
    RecyclerView.Adapter<SongsAdapter.SongsListViewHolder>() {

    private var mData = mutableListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: SongsListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    fun updateList(list: List<Song>) {
        if (list.isNotEmpty()) {
            val prevCount = itemCount
            mData.addAll(list)
            notifyItemRangeChanged(prevCount, list.size)
        }
    }

    inner class SongsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mItem: Song
        private val mBinding = ItemSongBinding.bind(itemView)

        init {
            mBinding.songSave.setOnClickListener { toggleSavedSong() }
            itemView.setOnClickListener { mListener.onItemClicked(mItem) }
        }

        fun bind(item: Song) {
            mItem = item

            with(mBinding) {
                songName.text = item.trackName
                songSave.isSelected = mItem.savedSong
            }
        }

        private fun toggleSavedSong() {
            val selected = mBinding.songSave.isSelected
            mBinding.songSave.isSelected = !selected
            mItem.savedSong = !selected
            mListener.saveSong(mItem)
        }
    }
}