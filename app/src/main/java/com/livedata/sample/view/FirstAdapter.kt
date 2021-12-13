package com.livedata.sample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livedata.sample.databinding.FirstItemViewBinding
import com.livedata.sample.model.User

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var users = mutableListOf<User>()

    fun setuserList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = FirstItemViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users[position]
       holder.binding.name.text = user.name
        Glide.with(holder.itemView.context).load(user.img).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class MainViewHolder(val binding: FirstItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

}
