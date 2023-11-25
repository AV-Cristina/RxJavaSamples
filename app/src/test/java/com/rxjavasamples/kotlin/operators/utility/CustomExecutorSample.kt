package com.rxjavasamples.kotlin.operators.utility

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.Executors

class CustomExecutorSample {
    @SuppressLint("CheckResult")
    @Test
    fun customExecutorSample() {
        val numberOfThreads = 20
        val executor = Executors.newFixedThreadPool(numberOfThreads)
        val scheduler = Schedulers.from(executor)
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .subscribeOn(scheduler)
            .doFinally(executor::shutdown)
            .subscribe { s -> println(s) }
    }
}