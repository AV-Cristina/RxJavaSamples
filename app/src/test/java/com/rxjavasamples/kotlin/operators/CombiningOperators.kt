package com.rxjavasamples.kotlin.operators


import android.annotation.SuppressLint
import com.rxjavasamples.kotlin.create.JustVsFromCallable.Companion.sleep
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class CombiningOperators {

    @Test
    fun mergeSample() {
        // Излучает данные каждую секунду
        val source1 = Observable.interval(1, TimeUnit.SECONDS)
            .take(2) // берем только первые 2 элемента
            .map { l -> l + 1 } // эмитит прошедшие секунды
            .map { l -> "Source 1: $l seconds" }

        // Излучает данные каждую 300 миллисекунд
        val source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map { l -> (l + 1) * 300 } // эмитит прошедшие миллисекунды
            .map { l -> "Source 2: $l milliseconds" }

        Observable.merge(source1, source2)
            .subscribe { i -> println("Received: $i") }

        // Ждём 5 секунд, чтобы метод не завершился раньше времени
        sleep(5000)
    }

    @Test
    fun combineLatestSample() {
        val source1 = Observable.interval(300, TimeUnit.MILLISECONDS)
        val source2 = Observable.interval(1, TimeUnit.SECONDS)

        Observable.combineLatest(
            source1,
            source2
        ) { l1, l2 -> "Source 1: $l1 Source 2: $l2" }
            .subscribe { s: String -> println(s) }

        sleep(3000)
    }
}