package com.rxjavasamples.kotlin.operators.creating

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test

class ObservableCreate {

    @Test
    fun createSample() {

        val source = Observable.create { emitter ->
            emitter.onNext("Java")
            emitter.onNext("Kotlin")
            emitter.onNext("Go")
            emitter.onNext("Cpp")
            emitter.onNext("Python")
            emitter.onComplete()
        }

        source.subscribe(object : Observer<String> {
            var d: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                this.d = d
                println("onSubscribe: " + Thread.currentThread().name)
            }

            override fun onError(e: Throwable) {
                println("onError: $e")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onNext(t: String) {
                println("onNext: $t")
            }
        })
    }
}