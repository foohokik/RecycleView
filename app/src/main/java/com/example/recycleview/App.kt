package com.example.recycleview

import android.app.Application
import com.example.recycleview.model.UserService

class App:Application() {

    val usersService = UserService ()
}