package com.rxjavasamples.kotlin.subjects

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.junit.Test

@SuppressLint("CheckResult")
class BehaviorSubjectSample {

    @Test
    fun behaviorSubjectSample() {
        val subject: Subject<String> = BehaviorSubject.create()

        // Observer 1 подписывается на события, которые будет отправлять subject
        subject.subscribe { s -> println("Observer 1: $s") }

        subject.onNext("Alpha")
        subject.onNext("Beta")
        subject.onNext("Gamma")

        // Observer 2 подписывается на события subject и получает
        // последнее отправленное subject-ом событие
        subject.subscribe { s -> println("Observer 2: $s") }
    }

    @Test
    fun behaviorSubjectWithDefaultValueSample() {
        val subject: Subject<Long> = BehaviorSubject.createDefault(-1L)

        // Observer подписывается на события, которые будет отправлять subject
        // и несмотря на то, subject еще не отправлял событий,
        // подписчик сразу получает событие заданное по умолчанию
        subject.subscribe { s -> println("Observer: $s") }
    }
}