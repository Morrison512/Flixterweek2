package com.example.flixterp2ar

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var media2ImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var knownForTextView: TextView
    private lateinit var knownForNameTextView: TextView
    private lateinit var detailTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.flix2Image)
        media2ImageView = findViewById(R.id.posterImage)
        titleTextView = findViewById(R.id.nameTV)
        knownForTextView = findViewById(R.id.knownForTV)
        knownForNameTextView = findViewById(R.id.knownForName)
        detailTextView = findViewById(R.id.descTV)

        // TODO: Get the extra from the Intent
        val flix = intent.getSerializableExtra(FLIX_EXTRA) as Flix

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = flix.nameTitle
        knownForTextView.text = flix.knownFor?.get(0)?.title
        knownForNameTextView.text = flix.knownFor?.get(0)?.name
        Log.e(TAG, "title found")
        detailTextView.text = flix.knownFor?.get(0)?.flixDescription


        // TODO: Load the media image
        Glide.with(mediaImageView)
            .load("https://image.tmdb.org/t/p/w500/" + flix.profilePath)
            .into(mediaImageView)

        Glide.with(media2ImageView)
            .load("https://image.tmdb.org/t/p/w500/" + flix.knownFor?.get(0)?.posterPath)
            .into(media2ImageView)
    }
}