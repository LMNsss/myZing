package com.ngoclm.myzing.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngoclm.myzing.base.entities.Playlist
import com.ngoclm.myzing.base.interaction.onClickListener
import com.ngoclm.myzing.databinding.ItemPlayListBinding

class PlaylistAdapter(
    private var buttonClickListener: onClickListener
) : RecyclerView.Adapter<PlaylistAdapter.PlayListViewHolder>() {
    private var ds: List<Playlist> = listOf()

    inner class PlayListViewHolder(var binding: ItemPlayListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlayListBinding.inflate(inflater, parent, false)
        return PlayListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {

        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(ds[position].img)
                .into(imgPlayList)
            tvPlayListName.text = ds[position].playlistName
            infoSource.text = ds[position].infoSource

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSong(ds: List<Playlist>) {
        this.ds = ds
        notifyDataSetChanged()
    }
}