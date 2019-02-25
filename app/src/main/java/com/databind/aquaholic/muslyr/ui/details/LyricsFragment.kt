package com.databind.aquaholic.muslyr.ui.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.databind.aquaholic.muslyr.R
import com.databind.aquaholic.muslyr.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.lyrics_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LyricsFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val lyricsViewModelFactory: LyricsViewModelFactory by instance()
    private lateinit var viewModel: LyricsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lyrics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        val safeArgs = arguments?.let { LyricsFragmentArgs.fromBundle(it) }
        val id = safeArgs?.id
        val trackName = safeArgs?.trackName
        val trackArtist = safeArgs?.trackArtist

        Log.d("myLog", "Id is: $id")
        (activity as AppCompatActivity).supportActionBar?.title = trackName
        (activity as AppCompatActivity).supportActionBar?.subtitle = trackArtist
        viewModel = ViewModelProviders.of(this, lyricsViewModelFactory).get(LyricsViewModel::class.java)
        bindUI(id, trackName, trackArtist)

    }

    private fun bindUI(id: Int?, trackName: String?, trackArtist: String?) = launch(Dispatchers.Main) {
        val trackLyrics = viewModel.getLyrics(id!!, trackName!!, trackArtist!!)
        trackLyrics.observe(this@LyricsFragment, Observer {
            if (it == null) return@Observer
            if (it.lyricsBody.isEmpty()) lyrics_text.text = "This Music Doesn't Have Lyrics"
            else lyrics_text.text = it.lyricsBody
            lyrics_text.movementMethod = ScrollingMovementMethod()
        })
    }
}
