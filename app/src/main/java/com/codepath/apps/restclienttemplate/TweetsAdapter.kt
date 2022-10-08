package com.codepath.apps.restclienttemplate

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.TimelineActivity.Companion.TAG
import com.codepath.apps.restclienttemplate.models.DetailedActivity
import com.codepath.apps.restclienttemplate.models.Tweet
import kotlin.coroutines.coroutineContext

class TweetsAdapter(val tweets: ArrayList<Tweet>, private val context: Context) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    fun clear() {
        tweets.clear()
        notifyDataSetChanged()
    }

    fun addAll(tweetList: List<Tweet>) {
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivProfileImage = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val tvUserName = itemView.findViewById<TextView>(R.id.tvUsername)
        val tvTweetBody = itemView.findViewById<TextView>(R.id.tweetBody)
        val tvTimestamp = itemView.findViewById<TextView>(R.id.tvTimestamp)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            //Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
            val tweet = tweets[adapterPosition]
            val intent = Intent(context, DetailedActivity::class.java)
            intent.putExtra("tweet",tweet)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetsAdapter.ViewHolder, position: Int) {
        val tweet : Tweet = tweets.get(position)
        holder.tvUserName.text = tweet.user?.name
        holder.tvTweetBody.text = tweet.body
        holder.tvTimestamp.text = tweet.createdAt
        Glide.with(holder.itemView).load(tweet.user?.publicImageURL).into(holder.ivProfileImage)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

}