package com.codepath.apps.restclienttemplate.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.R
import com.codepath.apps.restclienttemplate.TweetsAdapter


private const val TAG = "DetailedActivity"
class DetailedActivity : AppCompatActivity() {
    private lateinit var username : TextView
    private lateinit var tweetBody : TextView
    private lateinit var timestamp : TextView
    private lateinit var profilePicture : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        username = findViewById(R.id.tvUser)
        tweetBody = findViewById(R.id.tvBody)
        timestamp = findViewById(R.id.tvPostedtime)
        profilePicture = findViewById(R.id.ivProfilePic)

        val tweet = intent.getParcelableExtra<Tweet>("tweet") as Tweet
        username.text = tweet.user?.name
        tweetBody.text = tweet.body
        timestamp.text = tweet.createdAt
        Glide.with(this).load(tweet.user?.publicImageURL).into(profilePicture)
    }
}