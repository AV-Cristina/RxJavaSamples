package com.rxjavasamples.kotlin.subjects

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.junit.Test

class BehaviorSubjectSample {

    @SuppressLint("CheckResult")
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
}