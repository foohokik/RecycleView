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

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = ItemUserBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun onClick(v: View) {
    val user = v.tag as UserData
        when (v.id) {
            R.id.btnDelete -> actionListener.deleteUser(user)
            else -> actionListener.editUserClick(user)
        }
    }
}