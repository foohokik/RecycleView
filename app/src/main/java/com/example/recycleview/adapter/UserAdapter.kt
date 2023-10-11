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

<<<<<<< HEAD
class UserAdapter (private val actionListener: UserActionListener): RecyclerView.Adapter<UserAdapter.UserViewHolder> (), View.OnClickListener {

    var users: List <UserData> = mutableListOf()
        set(value) {
            val diffCallBack = UserDiffUtilCallBack (field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            field = value
            diffResult.dispatchUpdatesTo(this)

        }

class UserViewHolder (val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
     fun bind (user: UserData) = with(binding) {
         tvUserName.text = user.name
         tvSurname.text= user.surname
         tvPhoneNumber.text = user.phoneNumber
         itemView.tag = user
         btnDelete.tag = user
     }

=======
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


>>>>>>> a61dbb2 (Initial commit)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = ItemUserBinding.inflate(inflater, parent, false)
<<<<<<< HEAD
        binding.root.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)

        return UserViewHolder(binding)
=======
        return UserViewHolder(binding)

>>>>>>> a61dbb2 (Initial commit)
    }

    override fun getItemCount(): Int {
        return  users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
<<<<<<< HEAD
        holder.bind(users[position])
    }

    override fun onClick(v: View) {
    val user = v.tag as UserData
        when (v.id) {
            R.id.btnDelete -> actionListener.deleteUser(user)
            else -> actionListener.editUserClick(user)
        }
    }
=======
        holder.bind(users[position], actionListener)

    }

    fun getItems (newUsers: List<UserData>) {

        val diffCallBack = UserDiffUtilCallBack (users, newUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        users.clear()
        users.addAll(newUsers)
        diffResult.dispatchUpdatesTo(this)
    }


>>>>>>> a61dbb2 (Initial commit)
}