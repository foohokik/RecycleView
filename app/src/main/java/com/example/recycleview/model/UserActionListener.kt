package com.example.recycleview.model



interface UserActionListener {

    fun editUserClick(user: UserData)

    fun deleteUser (user: UserData)

}