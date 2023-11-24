package com.rxjavasamples.kotlin.operators.utility

import android.annotation.SuppressLint
import com.rxjavasamples.kotlin.operators.creating.JustVsFromCallable.Companion.sleep
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.TimeUnit

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
            // Переключение на поток io будет проигнорировано
            .subscribeOn(Schedulers.io())
            .subscribe { s -> println("Received $s on thread ${Thread.currentThread().name}") }
        sleep(5000)
    }

    @Test
    fun intervalSubscribeOnSample() {
        val interval = Observable.interval(1, TimeUnit.SECONDS)
        // Переключения на io не произойдет,
        // т к для оператора interval по умолчанию используется computation
        interval.subscribeOn(Schedulers.io())
        interval.subscribe { i -> println("Received $i on thread ${Thread.currentThread().name}") }
        sleep(5000)
    }

    @Test
    fun intervalWithParameterSubscribeOnSample() {
        // Если для оператора с дефолтным планировщиком, необходимо задать другой планировщик,
        // то его необходимо передать третьим аргументом при вызове оператора
        val interval = Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread())
        interval.subscribe { i -> println("Received $i on thread ${Thread.currentThread().name}") }
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
                println("Received $i on thread ${Thread.currentThread().name}")
            }
        sleep(5000)
    }

    @Test
    fun multipleObserveOnSample() {
        Observable.just(1, 2)
            .subscribeOn(Schedulers.computation())
            // Переключаемся на новый поток
            .observeOn(Schedulers.newThread())
            // Переключаемся на computation
            .observeOn(Schedulers.computation())
            // Подписчик получит результат в computation
            .subscribe { i ->
                println("Received $i on thread ${Thread.currentThread().name}")
            }
        sleep(3000)
    }

    @Test
    fun unsubscribeOnSample() {
        val disposable = Observable.interval(1, TimeUnit.SECONDS)
            // Если при отписке требуется выполнить какую-то тяжелую работу,
            .doOnDispose { println("Disposing on thread " + Thread.currentThread().name) }
            // то перед отпиской можно переключиться на другой планировщик.
            .unsubscribeOn(Schedulers.io())
            // По умолчанию interval отработает на Computation
            .subscribe { i ->
                println("Received $i on thread " + Thread.currentThread().name)
            }
        sleep(3000)
        disposable.dispose()
        sleep(3000)
    }
}