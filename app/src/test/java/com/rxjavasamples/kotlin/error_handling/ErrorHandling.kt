package com.rxjavasamples.kotlin.error_handling

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

@SuppressLint("CheckResult")
class ErrorHandling {

    @Test
    fun onErrorReturnItemSample() {
        Observable.just(5, 2, 4, 0, 3, 2)
            .map { i -> 10 / i }
            // В случае ошибки вместо неё вернется указанное значение
            .onErrorReturnItem(-1)
            .subscribe { i -> println("Received: $i") }
    }

    @Test
    fun onErrorResumeNextSample() {
        Observable.just(5, 2, 4, 0, 3, 2)
            .map { i -> 10 / i }
            // В случае ошибки вместо неё вернутся элементы из указанного источника
            .onErrorResumeNext { Observable.just(-1, -2, -3) }
            .subscribe { i -> println("Received: $i") }
    }

    @Test
    fun onRetryNextSample() {
        Observable.just(5, 2, 4, 0, 3, 2)
            .map { i -> 10 / i }
            // В случае ошибки произойдет переподписка на источник указанное количество раз.
            // Следует учитывать нагрузку на сервер, если у нас многопользовательский сервер и
            // не забывать ограничивать количество попыток, чтобы не заддосить его.
            .retry(1)
            .subscribe { i -> println("Received: $i") }
    }
}