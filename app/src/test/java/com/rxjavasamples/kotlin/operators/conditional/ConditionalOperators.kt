package com.rxjavasamples.kotlin.operators.conditional

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

@SuppressLint("CheckResult")
class ConditionalOperators {

    @Test
    fun distinctUntilChangedSample() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .map(String::length)
            .distinctUntilChanged()
            .subscribe { s -> println(s) }
    }

    @Test
    fun takeWhileSample() {
        val source = Observable.fromArray(1, 2, 3, 4, 5, 1, 0)
        source.takeWhile { x -> x < 4 }.subscribe { s -> println(s) }
    }

    @Test
    fun skipWhileSample() {
        val source = Observable.fromArray(1, 2, 3, 4, 5, 1, 0)
        source.skipWhile { x -> x < 4 }.subscribe { s -> println(s) }
    }
}