package com.gemini.githubfollowers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.gemini.githubfollowers.constants.BUNDLE_USERNAME
import com.gemini.githubfollowers.constants.USERNAME_QUERY
import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.view.FollowersAdapter
import com.gemini.githubfollowers.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.follower_recycle_view

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: GithubViewModel
    private lateinit var followersAdapter: FollowersAdapter
    private var lastSearchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        restoreInstance(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.isIconifiedByDefault = false
        searchView.isIconified = false
        searchView.queryHint = getString(R.string.search_hit)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        setSearchView(searchView)
        searchItem.expandActionView()

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                searchView.isIconified = false
                searchView.requestFocusFromTouch()
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                searchView.setQuery("", true)
                return true
            }
        })

        return true
    }


    private fun getFollowerList(username: String) {
        mainViewModel.fetchFollowers(username)
        mainViewModel.followers.observe(this, Observer { followerList ->
            prepareRecyclerView(followerList)
            title = (getString(R.string.home_activity_title))
        })

    }

    private fun setSearchView(searchView: SearchView) {
        if (!lastSearchQuery.isEmpty()) {
            searchView.setQuery(lastSearchQuery, false)

            if (!mainViewModel.followers.value.isNullOrEmpty())
                getFollowerList(lastSearchQuery)
        }

        searchView.setOnQueryTextFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                searchView.showKeyboard()
            } else {
                searchView.hideKeyboard()
            }
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getFollowerList(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                lastSearchQuery = newText
                return true
            }
        })

    }

    private fun prepareRecyclerView(followerList: List<Follower>) {

        followersAdapter = FollowersAdapter(followerList)

        follower_recycle_view.setLayoutManager(GridLayoutManager(this, 3))

        follower_recycle_view.setItemAnimator(DefaultItemAnimator())
        follower_recycle_view.setAdapter(followersAdapter)

        followersAdapter!!.onItemClick = { Follower ->

            val mIntent = Intent(this, UserDetailActivity::class.java)
            mIntent.putExtra(BUNDLE_USERNAME, Follower.login)
            startActivity(mIntent)
        }
    }

    fun View.showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun View.hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getWindowToken(), 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (!lastSearchQuery.isEmpty()) {
            outState.putString(USERNAME_QUERY, lastSearchQuery)
        }

        super.onSaveInstanceState(outState)
    }

    private fun setLastSearchQuery(query: String) {
        lastSearchQuery = query
    }

    private fun restoreInstance(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val query = savedInstanceState.getString(USERNAME_QUERY)
            if (query != null) {
                setLastSearchQuery(query)
            }
        }
    }
}
