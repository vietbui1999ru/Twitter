package com.codepath.apps.restclienttemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.models.Tweet

class TweetsAdapter(val tweets: ArrayList<Tweet>) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsAdapter.ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)

        //Infalte item layout
        val view = inflater.inflate(R.layout.item_tweet, parent, false)

        return ViewHolder(view)
    }
    //Populate data into item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get data model based on the position
        val tweet : Tweet = tweets[position]

        //Set item views based on views and data model

        holder.tvUsername.text= tweet.user?.name
        holder.tweetBody.text= tweet.body

        Glide.with(holder.itemView).load(tweet.user?.publicImageURL).into(holder.profileImg)
    }

    override fun getItemCount(): Int {

        return tweets.size

    }

    fun clear() {
        tweets.clear()
        notifyDataSetChanged()
    }

    fun addAll(tweetList : List<Tweet>) {
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val profileImg = itemView.findViewById<ImageView>(R.id.imageTweet)
        val tvUsername = itemView.findViewById<TextView>(R.id.ivUsername)
        val tweetBody = itemView.findViewById<TextView>(R.id.TweetBody)



    }

}