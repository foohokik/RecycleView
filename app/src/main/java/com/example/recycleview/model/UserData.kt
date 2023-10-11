package com.example.recycleview.model

<<<<<<< HEAD
data class UserData( val id: Int, val name: String, val surname: String, val phoneNumber: String)
=======
import java.io.Serializable

data class UserData( val id: Int, val name: String, val surname: String, val phoneNumber: String) :
    Serializable
>>>>>>> a61dbb2 (Initial commit)
