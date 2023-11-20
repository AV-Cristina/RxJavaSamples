package com.rxjavasamples.kotlin.operators

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class DifferentOperators {

    data class Book(
        val title: String,
        val author: String,
        val id: Long?,
        val price: Double,
        val location: String,
        val currency: String
    )

    private fun getBooksList(): List<Book> {
        return listOf(
            Book("Шантарам", "Грегори Дэвид Робертс", 1, 780.0, "Москва", "₽"),
            Book("Три товарища", "Эрих Мария Ремарк", 2, 480.0, "Москва", "₽"),
            Book("Цветы для Элджернона", "Даниел Киз", 3, 380.0, "Москва", "₽"),
            Book(" Атлант расправил плечи", "Айн Рэнд", 4, 880.0, "Ставрополь", "₽"),
            Book(" Атлант расправил плечи", "Айн Рэнд", 4, 580.0, "Сочи", "₽")
        )
    }

    @SuppressLint("CheckResult")
    @Test
    fun filterBooks() {
        val booksSource = Observable.fromIterable(getBooksList())
            .filter { b -> b.location != "Москва" && b.price > 400 }
            .map { b -> "${b.title} ${b.author}"}
            .distinct() // исключаем повторы

        booksSource.subscribe {
            println(it)
        }
    }
}