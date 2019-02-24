//TODO:Optimize Code
package com.databind.aquaholic.muslyr.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.databind.aquaholic.muslyr.R
import com.databind.aquaholic.muslyr.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MusicSearchListFragment : ScopedFragment(), KodeinAware, SearchView.OnQueryTextListener {
    override val kodein by closestKodein()
    private val searchViewModelFactory: MusicSearchListViewModelFactory by instance()
    lateinit var progressBar: ProgressBar
    private lateinit var viewModel: MusicSearchListViewModel
    private var recyclerView: RecyclerView? = null
    private val resId = R.anim.layout_animation_waterfall
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.search_fragment, container, false)
        progressBar = rootView.findViewById(R.id.loading_progressbar_search)
        progressBar.visibility = View.GONE
        viewModel = ViewModelProviders.of(this, searchViewModelFactory).get(MusicSearchListViewModel::class.java)
        recyclerView = rootView.findViewById(R.id.search_recycler_view) as? RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()


        search_toolbar.apply {
            setIconifiedByDefault(false)
            requestFocus()
            setOnQueryTextListener(this@MusicSearchListFragment)
            isIconified = false
            requestFocusFromTouch()
            bindUI()
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("myLog", "Make Retrofit Call")
        launch(Dispatchers.IO) {
            if (query == null)
                Toast.makeText(context, "Write query", Toast.LENGTH_SHORT).show()
            else
                viewModel.search(query)
        }
        return false
    }

    private fun bindUI() {
        viewModel.querySearch.observe(this@MusicSearchListFragment, Observer {
            launch(Dispatchers.Main) {
                progressBar.visibility = View.VISIBLE
                val searchedTracks = viewModel.searchForTracks(it)
                progressBar.visibility = View.GONE
                val adapter = MusicSearchListAdapter(
                    this@MusicSearchListFragment,
                    searchedTracks.message.body.trackList
                )
                val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, resId)
                recyclerView!!.layoutAnimation = animation
                recyclerView!!.adapter = adapter
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        })
    }
}
