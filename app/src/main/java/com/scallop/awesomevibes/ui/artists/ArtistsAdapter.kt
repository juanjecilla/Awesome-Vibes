package com.scallop.awesomevibes.ui.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scallop.awesomevibes.R
import com.scallop.awesomevibes.databinding.ItemArtistBinding
import com.scallop.awesomevibes.entities.Artist
import com.scallop.awesomevibes.ui.commons.OnItemClick

class ArtistsAdapter(private val mListener: OnItemClick<Artist>) :
    RecyclerView.Adapter<ArtistsAdapter.ArtistsListViewHolder>() {

    private var mData = mutableListOf<Artist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        return ArtistsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ArtistsListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    fun updateList(list: List<Artist>) {
        if (list.isNotEmpty()) {
            val prevCount = itemCount
            mData.addAll(list)
            notifyItemRangeChanged(prevCount, list.size)
        }
    }

    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    inner class ArtistsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mItem: Artist
        private val mBinding = ItemArtistBinding.bind(itemView)

        init {
            itemView.setOnClickListener { mListener.onItemClicked(mItem) }
        }

        fun bind(item: Artist) {
            mItem = item

            with(mBinding) {
                artistName.text = item.artistName
                artistGenre.text = item.primaryGenreName
            }
        }
    }
}