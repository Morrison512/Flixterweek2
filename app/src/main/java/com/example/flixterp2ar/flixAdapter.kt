package com.example.flixterp2ar

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val FLIX_EXTRA = "FLIX_EXTRA"
private const val TAG = "flixAdapter"

class flixAdapter(private val context: Context, private val flixs: List<Flix>) :
    RecyclerView.Adapter<flixAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_flix, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flix = flixs[position]
        holder.bind(flix)
    }

    override fun getItemCount() = flixs.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.flixImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.flixName)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(flix: Flix) {
            titleTextView.text = flix.nameTitle

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + flix.profilePath)
                .into(mediaImageView)
            Log.e(TAG, "bind check")
        }

        override fun onClick(v: View?) {
            val flix = flixs[absoluteAdapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FLIX_EXTRA, flix)
            context.startActivity(intent)
        }
    }
}