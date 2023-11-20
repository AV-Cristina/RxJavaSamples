package com.rxjavasamples.kotlin.create

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

@SuppressLint("CheckResult")
class JustVsFromCallable {

    @Test
    fun justDemo() {
        // Метод или лямбда выражение переданное just выполняется сразу же в том потоке,
        // в котором мы подписались и при новой подписке больше не выполняется
        val source = Observable.just(System.currentTimeMillis())
        source.subscribe { s -> println("Received: $s") }
        println("Sleeping 5s")
        sleep(5000) // ждем 5 секунд
        // При повторной подписке, будет выведено тоже, самое значение s
        source.subscribe { s -> println("Received: $s") }
    }

    @Test
    fun fromCallableDemoSample() {
        val source = Observable.fromCallable {
            // это выполняется только тогда,
            // когда вызывается метод subscribe()
            System.currentTimeMillis()
        }
        source.subscribe { s -> println("Received: $s") }
        println("Sleeping 5s")
        sleep(5000)
        source.subscribe { s -> println("Received: $s") }
    }

    companion object {

        fun sleep(millis: Long) {
            try {
                Thread.sleep(millis)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}