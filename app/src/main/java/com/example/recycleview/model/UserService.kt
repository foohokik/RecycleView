package com.example.recycleview.model

import com.github.javafaker.Faker
import java.util.Collections

class UserService {

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



}