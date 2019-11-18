package com.gemini.githubfollowers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.gemini.githubfollowers.constants.BUNDLE_USERNAME
import com.gemini.githubfollowers.constants.SHOW_IMAGE
import com.gemini.githubfollowers.constants.SHOW_INITIAL
import com.gemini.githubfollowers.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_user_detail.location
import kotlinx.android.synthetic.main.activity_user_detail.mail
import kotlinx.android.synthetic.main.avatar_layout.display_username
import kotlinx.android.synthetic.main.avatar_layout.followers
import kotlinx.android.synthetic.main.avatar_layout.following
import kotlinx.android.synthetic.main.avatar_layout.repositories
import kotlinx.android.synthetic.main.item_follower.avatar
import kotlinx.android.synthetic.main.item_follower.login

class UserDetailActivity : AppCompatActivity() {
    private var mainViewModel: GithubViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        mainViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)

        val mBundle = intent.extras
        if (mBundle != null) {
            var username: String? = ""
            if (mBundle.getString(BUNDLE_USERNAME) != null)
                username = mBundle.getString(BUNDLE_USERNAME)
            if (username != null) {
                getUserInfo(username)
            }
        }
    }


    fun getUserInfo(username: String) {
        mainViewModel?.fetchUserInfo(username)
        mainViewModel?.user?.observe(this, Observer { user ->
            avatar.state = SHOW_INITIAL
            avatar.state = SHOW_IMAGE
            Glide.with(this)
                .load(user.avatar_url)
                .into(avatar)

            login.text = user.login
            display_username.text = user.name
            followers.text = user.followers.toString()
            following.text = user.following.toString()
            repositories.text = user.public_repos.toString()
            location.text = user.location
            mail.text = user.email
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
