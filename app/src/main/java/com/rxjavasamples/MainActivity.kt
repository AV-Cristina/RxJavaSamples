package com.rxjavasamples

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rxjavasamples.adapters.MoviesAdapter
import com.rxjavasamples.network.MovieApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        recyclerView.layoutManager =LinearLayoutManager(this)

        val getTopRatedMovies = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        getTopRatedMovies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val movies = it.results
                    recyclerView.adapter = MoviesAdapter(movies, R.layout.item_movie_list)
                },
                { error -> Log.e(TAG, error.toString()) }
            )
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        // TODO: insert string with your themoviedb.org API KEY in file local.properties
        // string should look like this: THE_MOVIE_API="your API KEY here"
        private const val API_KEY = BuildConfig.API_URL
    }
}