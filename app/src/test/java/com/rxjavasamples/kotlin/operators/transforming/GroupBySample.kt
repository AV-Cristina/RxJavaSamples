package com.rxjavasamples.kotlin.operators.transforming

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.GroupedObservable
import org.junit.Test

class GroupBySample {

    @SuppressLint("CheckResult")
    @Test
    fun groupBySample() {
        val source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
        val byLengths: Observable<GroupedObservable<Int, String>> = source.groupBy { s -> s.length }
        byLengths.flatMapSingle { group -> group.toList() }
            .subscribe { println(it) }
    }
}