package com.example.recycleview.model
import java.io.Serializable

data class UserData( val id: Int, val name: String, val surname: String, val phoneNumber: String) :
    Serializable

