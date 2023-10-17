package com.example.recycleview.model

class UserRepository (private val userService: UserService) {

    fun getUsers (): List<UserData> = userService.getUsers()
}