package com.livedata.sample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livedata.sample.databinding.SecItemViewBinding
import com.livedata.sample.model.User

class SecondAdapter : RecyclerView.Adapter<SecViewHolder>() {

    var users = mutableListOf<User>()

    fun setuserList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = SecItemViewBinding.inflate(inflater, parent, false)
        return SecViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SecViewHolder, position: Int) {
        val user = users[position]
        holder.binding.txtViewTitle.text = user.name
        Glide.with(holder.itemView.context).load(user.img).into(holder.binding.imgViewIcon)

    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class SecViewHolder(val binding: SecItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

}