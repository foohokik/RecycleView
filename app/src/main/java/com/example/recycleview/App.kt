package com.example.recycleview

import android.app.Application
<<<<<<< HEAD
=======
import com.example.recycleview.model.UserRepository
>>>>>>> a61dbb2 (Initial commit)
import com.example.recycleview.model.UserService

class App:Application() {

    val usersService = UserService ()
<<<<<<< HEAD
=======
    val usersRepository = UserRepository(usersService) // Почему в конструктор репозитория добавили обьект сервиса?
>>>>>>> a61dbb2 (Initial commit)
}