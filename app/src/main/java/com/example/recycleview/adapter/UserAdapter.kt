package com.example.recycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.R
import com.example.recycleview.databinding.ItemUserBinding
import com.example.recycleview.model.UserActionListener
import com.example.recycleview.model.UserData

class UserAdapter (private val actionListener: UserActionListener): RecyclerView.Adapter<UserAdapter.UserViewHolder> () {

   private val users = mutableListOf<UserData>()  // почему users можно вызывать только в методе??


 class UserViewHolder (val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
     fun bind (user: UserData, actionListener: UserActionListener) = with(binding) {
         tvUserName.text = user.name
         tvSurname.text= user.surname
         tvPhoneNumber.text = user.phoneNumber

         btnDelete.setOnClickListener {
             actionListener.deleteUser(user)
         }
         root.setOnClickListener{
             actionListener.editUserClick(user)
         }

     }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return  users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position], actionListener)

    }

    fun getItems (newUsers: List<UserData>) {
        val diffCallBack = UserDiffUtilCallBack (users, newUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        users.clear()
        users.addAll(newUsers)
        diffResult.dispatchUpdatesTo(this)
    }

}