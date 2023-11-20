package com.rxjavasamples.kotlin.create

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test

class CreatingOperators {

    @Test
    fun justSample() {

        // Создание источника данных.
        // Оператор Just преобразует элемент в Observable,
        // который генерирует этот элемент.
        val months = Observable.just(
            "January", "February", "March",
            "April", "May", "June",
            "July", "August"
        )

        // Для получения данных, необходимо подписаться на источник данных,
        // вызвав метод subscribe и передав в него имплементацию интерфейса Observer
        months.subscribe(object : Observer<String> {

            override fun onSubscribe(d: Disposable) {
                println("\nonSubscribe: " + Thread.currentThread().name + "\n")
            }

            override fun onNext(s: String) {
                println("onNext: $s")
            }

            override fun onError(e: Throwable) {
                println("onError: $e")
            }

            override fun onComplete() {
                println("\nonComplete")
            }
        })
    }

    @Test
    fun fromIterableSample() {

        val months = listOf(
            "January", "February", "March",
            "April", "May", "June",
            "July", "August"
        )

        // Создание источника данных,
        // методом который в качестве аргумента принимает коллекции,
        // которые реализуют интерфейс Iterable, например, List, Set,Collection и т.д.
        val monthsIterable = Observable.fromIterable(months)

        // Подписка на источник данных
        // Результат будет такой же, как в предыдущем примере с just
        monthsIterable.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("\nonSubscribe: " + Thread.currentThread().name + "\n")
            }

            override fun onError(e: Throwable) {
                println("onError: $e")
            }

            override fun onComplete() {
                println("\nonComplete")
            }

            override fun onNext(t: String) {
                println("onNext: $t")
            }
        })
    }

    @Test
    fun fromArraySample() {
        val months = listOf(
            "January", "February", "March",
            "April", "May", "June",
            "July", "August"
        )

        val monthsArray = Observable.fromArray(months)

        // Подписка на источник данных
        // Здесь метод onNext() вызовется один раз и мы сразу же получим весь список
        monthsArray.subscribe(object : Observer<List<String>> {
            override fun onSubscribe(d: Disposable) {
                println("\nonSubscribe: " + Thread.currentThread().name + "\n")
            }

            override fun onError(e: Throwable) {
                println("onError: $e")
            }

            override fun onComplete() {
                println("\nonComplete")
            }

            override fun onNext(t: List<String>) {
                println("onNext: $t")
            }
        })
    }
}