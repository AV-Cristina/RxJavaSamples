package com.rxjavasamples.kotlin.subjects

import android.annotation.SuppressLint
import com.rxjavasamples.kotlin.operators.creating.JustVsFromCallable.Companion.sleep
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject
import io.reactivex.rxjava3.subjects.UnicastSubject
import org.junit.Test
import java.util.concurrent.TimeUnit

class UnicastSubjectSample {

    @SuppressLint("CheckResult")
    @Test
    fun unicastSubjectSample() {
        val subject: Subject<String> = UnicastSubject.create()

        Observable.interval(300, TimeUnit.MILLISECONDS)
            .map { l -> ((l + 1) * 300).toString() + " milliseconds" }
            .subscribe(subject)

        sleep(2000)
        // Здесь мы разом получим события пришедшие за 2000мс ожидания, а потом остальные
        subject.subscribe { s -> println("Observer 1: $s") }
        sleep(8000)
    }
}