package com.rxjavasamples.kotlin.subjects

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.Subject
import org.junit.Test

class AsyncSubjectSample {

    @SuppressLint("CheckResult")
    @Test
    fun asyncSubjectSample() {
        val subject: Subject<String> = AsyncSubject.create()

        // Observer 1 подписывается на события subject
        // и получает только то событие, которое предшествовало событию onComplete
        subject.subscribe(
            { s -> println("Observer 1: $s") },
            { e -> e.printStackTrace() }
        ) { println("Observer 1 done!") }

        subject.onNext("Alpha")
        subject.onNext("Beta")
        subject.onNext("Gamma")
        subject.onComplete()

        // Observer 2 подписывается на события subject,
        // и получает событие, которое предшествовало событию onComplete
        subject.subscribe(
            { s -> println("Observer 2: $s") },
            { e -> e.printStackTrace() }
        ) { println("Observer 2 done!") }
    }
}