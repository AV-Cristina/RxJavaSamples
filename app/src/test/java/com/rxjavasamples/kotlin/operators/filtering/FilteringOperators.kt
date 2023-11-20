package com.rxjavasamples.kotlin.operators.filtering

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

@SuppressLint("CheckResult")
class FilteringOperators {

    @Test
    fun distinctSample() {
        val source = Observable.fromArray(5, 4, 5, 6, 7, 2, 3, 4, 5, 1, 8, 9, 5)
        source.distinct().subscribe { s -> println(s) }
    }

    @Test
    fun distinctSample2() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .map(String::length)
            .distinct()
            .subscribe { s -> println(s) }
    }

    @Test
    fun elementAtSample() {
        val source = Observable.fromArray(1, 2, 3, 4, 5)
        source.elementAt(2).subscribe { s -> println(s) }
    }

    @Test
    fun takeSample() {
        val source = Observable.fromArray(1, 2, 3, 4)
        source.take(2).subscribe { s -> println(s) }
    }

    @Test
    fun skipSample() {
        val source = Observable.fromArray(1, 2, 3, 4)
        source.skip(2).subscribe { s -> println(s) }
    }
}