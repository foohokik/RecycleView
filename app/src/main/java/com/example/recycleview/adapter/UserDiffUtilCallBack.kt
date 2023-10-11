package com.example.recycleview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recycleview.model.UserData

class UserDiffUtilCallBack (
    private val oldList: List<UserData>
    ,private val newList: List<UserData>
):DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
       return oldUser.id == newUser.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }
}