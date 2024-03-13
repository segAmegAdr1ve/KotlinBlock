package com.example.kotlinblock

import android.util.Log
import java.util.Calendar


// 2
enum class Type {
    DEMO, FULL
}

// 3
data class User(val id: Int, val name: String, val age: Int, val type: Type) {
    val startTime by lazy { Calendar.getInstance().timeInMillis }
}

// 8
fun User.checkAge() {
    if (this.age >= 18) {
        Log.d("TAG2", "${this.name} older than 18")
    } else {
        throw Exception("${this.name} under 18")
    }
}

// 9
interface AuthCallback {
    fun authSuccess()
    fun authFailed()
}

// 10,11
// Скорее всего я что то не так понял но в задании не сказано что нужно передавать
// в функцию auth юзера и экземпляр класса реализующего AuthCallback но так как
// функция объявлена на верхнем уровне получить их можно только через параметры
inline fun auth(user: User, authCallback: AuthCallback, updateCache: () -> Unit) {
    try {
        user.checkAge()
        authCallback.authSuccess()
        updateCache.invoke()
    } catch (_: Exception) {
        authCallback.authFailed()
    }
}

fun updateCache() {
    Log.d("TAG2", "cache updated")
}

// 12
sealed class Action
class Registration : Action()
class Login(val user: User) : Action()
class Logout : Action()

// 13
fun doAction(action: Action, authCallback: AuthCallback) {
    when (action) {
        is Registration -> Log.d("TAG2", "Registration started")
        is Login -> auth(action.user, authCallback, ::updateCache)
        is Logout -> Log.d("TAG2", "Logout started")
    }
}

fun tasksForRunning2() {
    // 4
    val user = User(1, "name1", 25, Type.DEMO)
    Log.d("TAG2", "Start time: ${user.startTime}")
    Thread.sleep(1000)
    Log.d("TAG2", "Start time: ${user.startTime}")

    // 5
    val listOfUsers = mutableListOf(user).apply {
        add(User(2, "name2", 26, Type.FULL))
        add(User(3, "name3", 27, Type.DEMO))
    }

    // 6
    val usersWithFullAccess = listOfUsers.filter { it.type == Type.FULL }

    // 7
    val userNames = listOfUsers.map { it.name }
    Log.d("TAG2", "First item: ${userNames.first()} Last item: ${userNames.last()}")

    // 9
    val authCallback = object : AuthCallback {
        override fun authSuccess() {
            Log.d("TAG2", "auth success")
        }

        override fun authFailed() {
            Log.d("TAG2", "auth failed")
        }
    }
}