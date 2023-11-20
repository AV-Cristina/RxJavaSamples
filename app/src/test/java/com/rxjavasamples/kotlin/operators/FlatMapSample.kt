package com.rxjavasamples.kotlin.operators

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class FlatMapSample {

    @SuppressLint("CheckResult")
    @Test
    fun flatMapSample() {
        val initialSource = Observable.just("day")

        initialSource.flatMap {
            if (it == "day") {
                return@flatMap Observable.just("Monday", "Tuesday", "Sunday")
            } else {
                return@flatMap Observable.just("March", "April", "June")
            }
        }
            // Подписываемся, чтобы получить данные
            .subscribe { s -> println(s) }
    }
}