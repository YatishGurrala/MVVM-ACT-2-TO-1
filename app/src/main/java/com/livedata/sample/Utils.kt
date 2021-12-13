package com.livedata.sample

import androidx.lifecycle.MutableLiveData
import com.livedata.sample.model.User
import java.util.*

object Utils {
    fun changeFirstName(userList: MutableLiveData<List<User>>) {
        val array = userList.value?.toMutableList()
        val user = array?.get(0)
        user?.name = "New Yatish"
        user?.let { array.set(0, it) }
    }

    fun populateList(): ArrayList<User> {
        val userArrayList: ArrayList<User>?
        var user = User()
        user.name = "Yatish"
        user.description = generateRandomDescription(7)
        user.img = R.drawable.male_young_64
        userArrayList = ArrayList()
        userArrayList.add(user)
        user = User()
        user.name = "Jane Doe"
        user.description = generateRandomDescription(7)
        user.img = R.drawable.female_64
        userArrayList.add(user)
        user = User()
        user.name = "Kael Doe"
        user.description = generateRandomDescription(7)
        user.img = R.drawable.male_office_64
        userArrayList.add(user)
        user = User()
        user.name = "Teresa Doe"
        user.description = generateRandomDescription(7)
        user.img = R.drawable.feamle_avatar_64
        userArrayList.add(user)
        user = User()
        user.name = "Ronnie Doe"
        user.description = generateRandomDescription(7)
        user.img = R.drawable.male_grandma_64
        userArrayList.add(user)
        user = User()
        user.name = "Mary Doe"
        user.description = generateRandomDescription(7)
        user.img = R.drawable.female_afro_mp4
        userArrayList.add(user)
        return userArrayList
    }

    private fun generateRandomDescription(len: Int): String {
        val chars = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz!@#$%&")
        val rnd = Random()
        val sb = StringBuilder(len)
        for (i in 0 until len) sb.append(chars[rnd.nextInt(chars.length)])
        return sb.toString()
    }
}