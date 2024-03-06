package com.example.kotlinblock

import android.util.Log

interface Publication {
    val price: Int
    val wordCount: Int
    fun getType(): String
}

class Book(override val price: Int, override val wordCount: Int) : Publication {
    override fun getType(): String {
        return when {
            wordCount <= 1000 -> "Flash Fiction"
            wordCount <= 7500 -> "Short Story"
            else -> "Novel"
        }
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book
        if (price != other.price) return false
        if (wordCount != other.wordCount) return false

        return true
    }
}

class Magazine(override val price: Int, override val wordCount: Int) : Publication {
    override fun getType(): String {
        return "Magazine"
    }
}

fun buy(order: Publication) {
    Log.d("TAG", "The purchase is complete. The purchase amount was ${order.price}€")
}

fun tasksForRunning() {
    // Пункт задания №3
    val book1 = Book(50, 1500)
    val book2 = Book(100, 10000)
    val magazine = Magazine(1, 1)
    Log.d("TAG", """
        book 1:
            Type: ${book1.getType()}
            Word count: ${book1.wordCount}
            Price: ${book1.price}€ 
    """.trimIndent())

    Log.d("TAG", """
        book 2:
            Type: ${book2.getType()}
            Word count: ${book2.wordCount}
            Price: ${book2.price}€ 
    """.trimIndent())

    Log.d("TAG", """
        magazine:
            Type: ${magazine.getType()}
            Word count: ${magazine.wordCount}
            Price: ${magazine.price}€ 
    """.trimIndent())

    Log.d("TAG", "book1.equals(book2) => ${book1.equals(book2)}")
    Log.d("TAG", "book1 === book2 => ${book1 === book2}")

    // Пункт задания №4
    val book3: Book? = Book(50, 1500)
    val book4: Book? = null
    book3?.let { book -> buy(book) }
    book4?.let { book -> buy(book) }

    // Пункт задания №5
    val sum = {a: Int, b: Int -> Log.d("TAG", "Sum result: ${a + b}") }
    sum.invoke(5, 7)
}