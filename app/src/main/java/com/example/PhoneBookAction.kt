package com.example

import com.example.recycleview.model.UserData

sealed class PhoneBookAction {

    class EditUser (val user: UserData) : PhoneBookAction()
    object AddUser : PhoneBookAction()
}
