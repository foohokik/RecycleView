package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.adapter.UserAdapter
import com.example.recycleview.databinding.ActivityMainBinding
import com.example.recycleview.model.UserActionListener
import com.example.recycleview.model.UserData
import com.example.recycleview.model.UserService
import com.example.recycleview.model.UsersListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter

   private val userService:UserService
        get()= (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter(object : UserActionListener {
            override fun editUserClick(user: UserData) {
            Toast.makeText(this@MainActivity, "jrgnfkv", Toast.LENGTH_SHORT).show()
            }

            override fun deleteUser(user: UserData) {
            userService.removeUser(user)
            }

        })
        val layoutManager = LinearLayoutManager(this)
        binding.rcView.layoutManager = layoutManager
        binding.rcView.adapter = adapter

        userService.addListener(userListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        userService.removeListener(userListener)
    }

    private val userListener: UsersListener = {
        adapter.users = it
    }
}