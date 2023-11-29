package com.rxjavasamples.kotlin.subjects

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.subjects.Subject
import org.junit.Test

class ReplaySubjectSample {

    @SuppressLint("CheckResult")
    @Test
    fun replaySubjectSample() {
        val subject: Subject<String> = ReplaySubject.create()

        // Observer 1 подписывается на события, которые будет отправлять subject
        subject.subscribe { s -> println("Observer 1: $s") }

        subject.onNext("Alpha")
        subject.onNext("Beta")
        subject.onNext("Gamma")
        subject.onComplete()

        // Observer 2 подписывается на события subject
        // и получаем все переданные им ранее события
        subject.subscribe { s -> println("Observer 2: $s") }
    }
}