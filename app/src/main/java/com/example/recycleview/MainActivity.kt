package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.adapter.UserAdapter
import com.example.recycleview.databinding.ActivityMainBinding
import com.example.recycleview.model.UserActionListener
import com.example.recycleview.model.UserData
import com.example.recycleview.model.UserService
import com.example.recycleview.model.UsersListener
=======
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.PhoneBookAction
import com.example.recycleview.adapter.UserAdapter
import com.example.recycleview.databinding.ActivityMainBinding
import com.example.recycleview.model.DialogFragment
import com.example.recycleview.model.UserData

>>>>>>> a61dbb2 (Initial commit)

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter

<<<<<<< HEAD
   private val userService:UserService
        get()= (applicationContext as App).usersService
=======
    private val viewModel: UserViewModel by viewModels() {UserViewModel.Factory}


>>>>>>> a61dbb2 (Initial commit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD
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
=======
        initView()
        observe()
        binding.btnAdd.setOnClickListener{
            viewModel.onAddClick()
        }
    }



    private fun observe() {
        val listOfUserObserver = Observer<List<UserData>> { items ->
            adapter.getItems(items)
        }

        val sideEffectObserver = Observer<PhoneBookAction> {  action ->
            val user: UserData?
            when (action) {
                is PhoneBookAction.AddUser -> {
                     user = null
                }
                is PhoneBookAction.EditUser -> {
                    user = action.user
                }
            }

            DialogFragment.newInstance(user).show(supportFragmentManager, DialogFragment.TAG)
        }


        viewModel.sideEffectLiveData.observe(this, sideEffectObserver)
        viewModel.usersLiveData.observe(this, listOfUserObserver)
    }

    private fun initView () {
        val layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(viewModel)
        binding.rcView.layoutManager = layoutManager
        binding.rcView.adapter = adapter
    }

>>>>>>> a61dbb2 (Initial commit)
}