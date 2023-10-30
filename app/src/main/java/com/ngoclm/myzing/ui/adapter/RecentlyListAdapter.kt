package com.ngoclm.myzing.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.interaction.onClickListener
import com.ngoclm.myzing.databinding.RecentlyListItemBinding

class RecentlyListAdapter(
    private var onClickListener: onClickListener
) : RecyclerView.Adapter<RecentlyListAdapter.ListSongViewHolder>() {
    private var ds: List<Song> = listOf()

    inner class ListSongViewHolder(var binding: RecentlyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            itemView.setOnClickListener { onClickListener.onClickItem(song) }
            binding.apply {
                Glide.with(itemView.context)
                    .load(song.img)
                    .into(imgRecentlySong)
                tvListenRecentlySong.text = song.songName

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecentlyListItemBinding.inflate(inflater, parent, false)
        return ListSongViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: ListSongViewHolder, position: Int) {

        holder.bind(ds[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSong(ds: List<Song>) {
        this.ds = ds
        notifyDataSetChanged()
    }
}