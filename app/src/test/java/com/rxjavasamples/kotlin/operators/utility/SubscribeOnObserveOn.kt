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
                println("Received $i on thread " + Thread.currentThread().name)
            }
        sleep(5000)
    }

    @Test
    fun multipleSubscribeOnSample() {
        Observable.just("Red", "Orange", "Yellow", "Green", "Blue", "Purple")
            // Сработает наиболее близкое к источнику переключение на указанный поток
            .subscribeOn(Schedulers.computation())
            .map(String::length)
            .filter { l -> l > 4 }
            // Переключение на поток io() будет проигнорировано
            .subscribeOn(Schedulers.io())
            .subscribe { s -> println("Received $s on thread ${Thread.currentThread().name}") }
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
                println("Received $i on thread " + Thread.currentThread().name)
            }
        sleep(5000)
    }
}