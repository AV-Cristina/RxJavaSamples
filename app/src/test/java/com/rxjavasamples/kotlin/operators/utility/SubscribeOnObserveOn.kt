package com.rxjavasamples.kotlin.operators.utility

import android.annotation.SuppressLint
import com.rxjavasamples.kotlin.operators.creating.JustVsFromCallable.Companion.sleep
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

@SuppressLint("CheckResult")
class SubscribeOnObserveOn {

    @Test
    fun subscribeOnSample() {
        val source = Observable.just(1, 2)
        source
            // Источник будет передавать элементы в поток computation
            .subscribeOn(Schedulers.computation())
            .subscribe { i ->
                println("Received " + " on thread " + Thread.currentThread().name)
            }
        sleep(5000)
    }

    @Test
    fun observeOnSample() {
        val source = Observable.just(1, 2)
        source
            // Источник будет передавать элементы в поток computation
            .subscribeOn(Schedulers.computation())
            // Создает новый поток, переключаемся на него, получаем результат
            .observeOn(Schedulers.newThread())
            .subscribe { i ->
                println("Received " + " on thread " + Thread.currentThread().name)
            }
        sleep(5000)
    }
}