package com.scallop.awesomevibes.ui.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.ItemSongBinding
import com.scallop.awesomevibes.entities.Song

class SongsAdapter(private val mListener: OnSongItemInteractor) :
    ListAdapter<Song, SongsAdapter.SongsListViewHolder>(SongDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongsListViewHolder, position: Int) {
        holder.bind(getItem(position))
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

    class SongDiffCallback : DiffUtil.ItemCallback<Song>() {

        override fun areItemsTheSame(oldItem: Song, newItem: Song) =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Song, newItem: Song) =
            oldItem == newItem
    }
}