package com.gemini.githubfollowers.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemini.githubfollowers.R
import com.gemini.githubfollowers.constants.SHOW_IMAGE
import com.gemini.githubfollowers.constants.SHOW_INITIAL
import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.view.avatar.AvatarImageView

class FollowersAdapter(val followerList: List<Follower>) :
        RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {
    var onItemClick: ((Follower) -> Unit)? = null

    override fun getItemCount() = followerList.size

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.mContext = parent.context;

        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_follower,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val follower = followerList!!.get(position);
        holder.avatarImageView.state = SHOW_INITIAL

        holder.avatarImageView.state = SHOW_IMAGE
        mContext?.let {
            Glide.with(it)
                    .load(follower.avatar_url)
                    .into(holder.avatarImageView)

        }

        holder.login.text = follower.login
        holder.avatarImageView.setOnClickListener {
            onItemClick?.invoke(follower)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: AvatarImageView = itemView.findViewById(R.id.avatar);
        val login: TextView = itemView.findViewById(R.id.login);

    }
}