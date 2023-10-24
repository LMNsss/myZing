package com.ngoclm.myzing.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.databinding.RecentlyListItemBinding
import com.ngoclm.myzing.base.interaction.onClickListener

class RecentlyListAdapter( private var buttonClickListener: onClickListener
) : RecyclerView.Adapter<RecentlyListAdapter.ListSongViewHolder>() {
    private var ds:List<Song> = listOf()
    inner class ListSongViewHolder(var binding: RecentlyListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecentlyListItemBinding.inflate(inflater, parent, false)
        return ListSongViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: ListSongViewHolder, position: Int) {

        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(ds[position].img)
                .into(imgRecentlySong)
            tvListenRecentlySong.text = ds[position].songName
            holder.itemView.setOnClickListener() {
                buttonClickListener.onClickItem(it, position)
            }
        }
    }
    fun setSong(ds: List<Song>){
        this.ds = ds
        notifyDataSetChanged()
    }
}