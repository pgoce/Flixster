package com.example.flixster

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "MovieAdapter"
class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val ivOverlayPlay = itemView.findViewById<ImageView>(R.id.ivOverlayPlay)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie:Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ) {
                Glide.with(context).load(movie.posterImageUrl).placeholder(R.drawable.placeholderimage).centerCrop().transform(RoundedCorners(50)).into(ivPoster)
                if(movie.voteAverage >= 7.5) {
                    Glide.with(context).load(R.drawable.youtubeplay).into(ivOverlayPlay)
                }
                else {
                    Glide.with(context).load(R.drawable.transparent).into(ivOverlayPlay)
                }
            }
            else {
                Glide.with(context).load(movie.backdropImageUrl).placeholder(R.drawable.placeholderimage).centerCrop().transform(RoundedCorners(50)).into(ivPoster)
                if(movie.voteAverage >= 7.5) {
                    Glide.with(context).load(R.drawable.youtubeplay).into(ivOverlayPlay)
                }
                else{
                    Glide.with(context).load(R.drawable.transparent).into(ivOverlayPlay)
                }
            }



        }

        override fun onClick(p0: View?) {
            val movie = movies[adapterPosition]
//            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
