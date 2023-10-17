package com.example.recycleview

import android.app.Application
import com.example.recycleview.model.UserRepository
import com.example.recycleview.model.UserService


class App:Application() {

    val usersService = UserService ()

    val usersRepository = UserRepository(usersService) // Почему в конструктор репозитория добавили обьект сервиса?

}