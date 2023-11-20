package com.rxjavasamples.kotlin.operators

import android.annotation.SuppressLint
import com.rxjavasamples.kotlin.create.JustVsFromCallable.Companion.sleep
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

class DebounceSample {

    @SuppressLint("CheckResult")
    @Test
    fun debounceSample() {

        val source1 =
            Observable.interval(50, TimeUnit.MILLISECONDS)
                .map { i -> "Red $i" } // маппим прошедшее время в строку
                .take(3)
                .doOnNext { x -> println(x) }

        val source2 =
            Observable.interval(260, TimeUnit.MILLISECONDS)
                .map { i: Long -> "Green $i" }
                .take(3)
                .doOnNext { x -> println(x) }

        val source3 =
            Observable.interval(151, TimeUnit.MILLISECONDS)
                .map { i -> "Blue $i" }
                .take(3)
                .doOnNext { x -> println(x) }

        Observable.merge(source1, source2, source3)
            .debounce(100, TimeUnit.MILLISECONDS)
            .subscribe { x -> println("Result: $x") }

        sleep(5000)
    }
}