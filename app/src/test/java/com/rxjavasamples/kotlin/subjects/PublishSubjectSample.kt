package com.rxjavasamples.kotlin.subjects

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import org.junit.Test

class PublishSubjectSample {

    @SuppressLint("CheckResult")
    @Test
    fun publishSubjectSample() {
        val subject: Subject<String> = PublishSubject.create()

        // Observer 1 подписывается на события, которые будет отправлять subject
        // и получит события отправленные после подписки
        subject.subscribe { s -> println("Observer 1: $s") }

        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.onComplete()

        // Observer 2 подписывается на события subject,
        // но т.к. subject уже завершил отправку событий,
        // то Observer 2 здесь ничего не получит
        subject.subscribe { s -> println("Observer 2: $s") }
    }
}