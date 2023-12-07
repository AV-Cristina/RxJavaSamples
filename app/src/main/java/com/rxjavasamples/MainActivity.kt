package com.rxjavasamples

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rxjavasamples.adapters.MoviesAdapter
import com.rxjavasamples.data.Movie
import com.rxjavasamples.network.MovieApiClient
import com.rxjavasamples.ui.SearchBarView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.movies_recycler_view) }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val searchToolbar = findViewById<SearchBarView>(R.id.search_toolbar)

        // Поиск фильмов по запросу пользователя
        searchToolbar
            .onTextChangedObservable
            .map { it.trim() } // удаляем пробелы вначале и в конце текста, для более точного поиска
            .doOnNext { Log.d("THREAD: ", Thread.currentThread().name) }
            .debounce(500, TimeUnit.MILLISECONDS) // добавляем задержку перед отправкой запроса, чтобы не заддосить сервер большим кол-вом запросов
            .doOnNext { Log.d("THREAD after debounce: ", Thread.currentThread().name) }
            .filter { it.length > 3 }  // фильтруем запросы длинной не менее 3 символов
            .doOnNext { Log.d("THREAD: ", Thread.currentThread().name) }
            .observeOn(Schedulers.io()) // переключаемся на io для выполнения запроса в сеть
            .flatMapSingle {
                Log.d("THREAD: ", Thread.currentThread().name)
                MovieApiClient.apiClient.getMovie(API_KEY, it, "ru")
            }
            .doOnNext { Log.d("THREAD  after flatMap: ", Thread.currentThread().name) }
            .observeOn(AndroidSchedulers.mainThread()) // переключаемся на main, чтобы обновить UI
            .subscribe({
                setMovies(it.results)
                Log.d(TAG, it.toString())
            }, {
                Log.e(TAG, it.toString())
            })

        // Запрашиваем и выводим на экран список фильмов с высоким рейтингом
        val getTopRatedMovies = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        getTopRatedMovies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { setMovies(it.results) },
                { error -> Log.e(TAG, error.toString()) }
            )
    }

    private fun setMovies(movies: List<Movie>) {
        recyclerView.adapter = MoviesAdapter(movies, R.layout.item_movie_list)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        // TODO: В local.properties необходимо добавить строку с вашим API KEY
        // строка должна быть следующего вида: THE_MOVIE_API="your API KEY here"
        private const val API_KEY = BuildConfig.API_URL
    }
}