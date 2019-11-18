package com.gemini.githubfollowers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.gemini.githubfollowers.constants.BUNDLE_USERNAME
import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.view.FollowersAdapter
import com.gemini.githubfollowers.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.follower_recycle_view

class MainActivity : AppCompatActivity() {
    private var mainViewModel: GithubViewModel? = null
    private var followersAdapter: FollowersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }


    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            getFollowerList(query)
        }
    }

    private fun getFollowerList(username: String) {
        mainViewModel?.fetchFollowers(username)
        mainViewModel?.followers?.observe(this, Observer { followerList ->
            prepareRecyclerView(followerList)
            title = (getString(R.string.home_activity_title))
        })

    }

    private fun prepareRecyclerView(blogList: List<Follower>?) {

        followersAdapter = FollowersAdapter(blogList)

        follower_recycle_view.setLayoutManager(GridLayoutManager(this, 3))

        follower_recycle_view.setItemAnimator(DefaultItemAnimator())
        follower_recycle_view.setAdapter(followersAdapter)

        followersAdapter!!.onItemClick = { Follower ->

            val mIntent = Intent(this, UserDetailActivity::class.java)
            mIntent.putExtra(BUNDLE_USERNAME, Follower.login)
            startActivity(mIntent)
        }
    }
}
