package com.example.recycleview.model

import com.github.javafaker.Faker
import java.util.Collections


typealias UsersListener = (users:List<UserData>) -> Unit

class UserService {


    private var listeners = mutableListOf<UsersListener>()

    private var users = mutableListOf<UserData>()

    init {
        val faker: Faker = Faker.instance()
            users = (1..100).map {UserData(
            id = it.toInt(),
            name = faker.name().firstName(),
            surname = faker.name().lastName(),
            phoneNumber =  faker.phoneNumber().phoneNumber()
        )}.toMutableList()
    }

    fun getUsers(): List<UserData> {
        return users
    }

    fun removeUser (user:UserData) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users = ArrayList(users)
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser (user:UserData, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex != -1) return
        val newIndex = oldIndex +moveBy
        if (newIndex < 0 || newIndex>=users.size) return
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener (listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener (listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(users) }
    }

}