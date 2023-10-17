package com.example.recycleview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.PhoneBookAction
import com.example.recycleview.model.UserActionListener
import com.example.recycleview.model.UserData
import com.example.recycleview.model.UserRepository

class UserViewModel (repository: UserRepository): ViewModel(),  UserActionListener{

    private val _usersLiveData = MutableLiveData<List<UserData>>()
    val usersLiveData: LiveData<List<UserData>> = _usersLiveData

    private val _sideEffectLiveData = MutableLiveData<PhoneBookAction>()
    val sideEffectLiveData: LiveData<PhoneBookAction> = _sideEffectLiveData

    init {

   _usersLiveData.value = repository.getUsers()

    }



    fun onAddUser (name: String, surname: String, number: String) {
        val newUsers = mutableListOf<UserData>()
        val id = usersLiveData.value?.last()?.id?.plus(1)
        usersLiveData.value?.let {newUsers.addAll(it)}
        id?.let { newUsers.add(UserData(it, name, surname, number)) }
        _usersLiveData.value = newUsers
    }


    fun onEditUser (id:Int, name: String, surname: String, number: String) {
        val newUsers = mutableListOf<UserData>()
        val newUser = UserData(id, name, surname, number)
        usersLiveData.value?.let {newUsers.addAll(it)}
        newUsers.removeAt(id)
        newUsers.add(newUser)
        _usersLiveData.value = newUsers
    }

    override fun editUserClick(user: UserData) {
    _sideEffectLiveData.value = PhoneBookAction.EditUser(user)
    }

    fun onAddClick () {
        _sideEffectLiveData.value = PhoneBookAction.AddUser
    }

    override fun deleteUser(user: UserData) {
        val newUsers = mutableListOf<UserData>()
        usersLiveData.value?.let { newUsers.addAll(it) }
        newUsers.remove(user)
        _usersLiveData.value = newUsers
    }


    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return UserViewModel(
                    (application as App).usersRepository
                ) as T
            }
        }
    }



}