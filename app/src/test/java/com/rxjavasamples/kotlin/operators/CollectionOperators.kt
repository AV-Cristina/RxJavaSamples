package com.rxjavasamples.kotlin.operators

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

@SuppressLint("CheckResult")
class CollectionOperators {

    @Test
    fun collectSample() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .collect( { HashSet<Any>() }, { objects, e -> objects.add(e) } )
            .subscribe { s -> println("Received: $s") }
    }
}