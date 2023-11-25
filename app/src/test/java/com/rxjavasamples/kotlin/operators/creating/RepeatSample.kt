package com.rxjavasamples.kotlin.operators.creating

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class RepeatSample {

    @SuppressLint("CheckResult")
    @Test
    fun repeatSample() {
        Observable.just(1, 2, 3)
            .repeat(2)
            .subscribe { i -> println("Received: $i") }
    }
}