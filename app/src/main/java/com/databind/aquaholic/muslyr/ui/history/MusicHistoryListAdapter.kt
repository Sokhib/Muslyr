package com.databind.aquaholic.muslyr.ui.history


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.databind.aquaholic.muslyr.R
import com.databind.aquaholic.muslyr.data.db.entity.Lyrics
import com.databind.aquaholic.muslyr.internal.glide.GlideApp

class MusicHistoryListAdapter(
    private val context: MusicHistoryListFragment,
    private val lyrics: List<Lyrics>
) : RecyclerView.Adapter<MusicHistoryListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHistoryListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.featured_list_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = lyrics.size

    override fun onBindViewHolder(holder: MusicHistoryListAdapter.ViewHolder, position: Int) {
        holder.listItem.setOnClickListener {
            Log.d("myLog", "Position is: $position")
        }
        holder.trackName.text = lyrics[position].trackName
        holder.artist.text = lyrics[position].trackArtist
        //holder.musicImageView.setImageResource(R.drawable.baseline_queue_music_black_48dp)
        // Not optimal way though, image should come from API, now from VK
        GlideApp.with(context)
            .load("https://pp.userapi.com/c622727/v622727991/1dff3/1JdGhZ7yMr0.jpg")
            .centerInside()
            .into(holder.musicImageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackName = itemView.findViewById<TextView>(R.id.track_name)!!
        val artist = itemView.findViewById<TextView>(R.id.artist_name)!!
        val musicImageView = itemView.findViewById<ImageView>(R.id.imageView)!!
        val listItem = itemView.findViewById<ConstraintLayout>(R.id.cons_list_item)!!

    }
}