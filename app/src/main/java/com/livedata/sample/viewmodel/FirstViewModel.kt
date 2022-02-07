package com.livedata.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livedata.sample.model.User
import com.livedata.sample.Utils

class FirstViewModel : ViewModel() {
    //Declare the live data obejct
    var userList = MutableLiveData<List<User>>()

    //Declare the errorMessage obejct
    val errorMessage = MutableLiveData<String>()

    fun getAllUsers() {
      
        userList.value = Utils.populateList()

    }
}