package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.PhoneBookAction
import com.example.recycleview.adapter.UserAdapter
import com.example.recycleview.databinding.ActivityMainBinding
import com.example.recycleview.model.DialogFragment
import com.example.recycleview.model.UserData



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter

    private val viewModel: UserViewModel by viewModels() {UserViewModel.Factory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

}