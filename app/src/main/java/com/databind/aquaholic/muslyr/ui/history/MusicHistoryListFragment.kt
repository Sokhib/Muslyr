package com.databind.aquaholic.muslyr.ui.history

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
import kotlinx.android.synthetic.main.history_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MusicHistoryListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val historyViewModelFactory: MusicHistoryListViewModelFactory by instance()
    private val resId = R.anim.layout_animation_waterfall
    private lateinit var viewModel: MusicHistoryListViewModel
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.history_fragment, container, false)
        recyclerView = rootView.findViewById(R.id.history_recycler_view) as? RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.subtitle = ""
        viewModel = ViewModelProviders.of(this, historyViewModelFactory).get(MusicHistoryListViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = launch {
        history_loading_progressbar.visibility = View.VISIBLE
        val viewedTracks = viewModel.viewedTracks
        viewedTracks.observe(viewLifecycleOwner, Observer {
            if (it == null || it.isEmpty())
                return@Observer
            val adapter = MusicHistoryListAdapter(this@MusicHistoryListFragment, it)
            val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, resId)
            recyclerView!!.layoutAnimation = animation
            recyclerView!!.adapter = adapter

        })
        history_loading_progressbar.visibility = View.GONE
    }

}
