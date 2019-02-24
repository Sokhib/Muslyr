package com.databind.aquaholic.muslyr.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.databind.aquaholic.muslyr.R
import com.databind.aquaholic.muslyr.data.db.entity.Track
import com.databind.aquaholic.muslyr.internal.glide.GlideApp

class MusicSearchListAdapter(
    private val context: MusicSearchListFragment,
    private val tracks: List<Track>
) : RecyclerView.Adapter<MusicSearchListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.featured_list_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.trackName.text = tracks[position].track.trackName
        holder.artist.text = tracks[position].track.artistName
        holder.listItem.setOnClickListener {
            Log.d("myLog", "Position is: $position")
            val actionSearchDetail =
                MusicSearchListFragmentDirections.actionSearchDetail().setId(tracks[position].track.trackId)
                    .setTrackArtist(tracks[position].track.artistName)
                    .setTrackName(tracks[position].track.trackName)
            Navigation.findNavController(holder.listItem).navigate(actionSearchDetail)

        }
        //holder.musicImageView.setImageResource(R.drawable.baseline_queue_music_black_48dp)
        // Not optimal way though, image should come from API
        GlideApp.with(context)
            .load("https://pp.userapi.com/c622727/v622727991/1dff3/1JdGhZ7yMr0.jpg")
            .centerInside()
            .into(holder.musicImageView)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackName = itemView.findViewById<TextView>(R.id.track_name)!!
        val artist = itemView.findViewById<TextView>(R.id.artist_name)!!
        val listItem = itemView.findViewById<ConstraintLayout>(R.id.cons_list_item)!!
        val musicImageView = itemView.findViewById<ImageView>(R.id.imageView)!!
    }
}