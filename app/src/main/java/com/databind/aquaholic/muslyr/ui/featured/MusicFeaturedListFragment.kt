package com.databind.aquaholic.muslyr.ui.featured

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.databind.aquaholic.muslyr.R
import com.databind.aquaholic.muslyr.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.featured_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MusicFeaturedListFragment : ScopedFragment(), KodeinAware {
    // UI must be done with DataBinding

    override val kodein by closestKodein()
    private val featuredViewModelFactory: MusicFeaturedListViewModelFactory by instance()
    private lateinit var viewModel: MusicFeaturedListViewModel
    private val resId = R.anim.layout_animation_waterfall
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.featured_fragment, container, false)
        recyclerView = rootView.findViewById(R.id.featured_recycler_view) as? RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.subtitle = ""
        viewModel = ViewModelProviders.of(this, featuredViewModelFactory).get(MusicFeaturedListViewModel::class.java)
        bindUI()
    }


    private fun bindUI() = launch {
        val topTracks = viewModel.tracks.await()
        topTracks.observe(this@MusicFeaturedListFragment, Observer {
            if (it == null) return@Observer
            loading_progressbar.visibility = View.GONE
            //Load List
            val adapter = MusicFeaturedListAdapter(this@MusicFeaturedListFragment, it)
            val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, resId)
            recyclerView!!.layoutAnimation = animation
            recyclerView!!.adapter = adapter
            //


        })
    }

}
