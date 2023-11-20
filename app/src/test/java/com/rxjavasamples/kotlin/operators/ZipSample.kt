package com.rxjavasamples.kotlin.operators

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test


class ZipSample {

    @SuppressLint("CheckResult")
    @Test
    fun zipSample() {
        // Источник данных, имитирующий ответ от сервера - имени пользователя
        val source1 = Observable.just(User("John", "Wick"))
        // Источник для получения данных паспорта
        val source2 = Observable.just(UserPassport("2.09.1964", "New York"))

        // Объединяем данные
        Observable.zip(source1, source2) { user, passport ->
            UserData(user.name, passport.date, passport.address)
        // Подписываемся, чтобы получить данные
        }.subscribe { s -> println(s) }
    }
}

data class User(
    val name: String,
    val surname: String
)

data class UserPassport(
    val date: String,
    val address: String
)

data class UserData(
    val name: String,
    val date: String,
    val address: String
)