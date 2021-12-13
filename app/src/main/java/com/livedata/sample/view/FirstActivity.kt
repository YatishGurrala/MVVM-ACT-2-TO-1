package com.livedata.sample.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livedata.sample.R
import com.livedata.sample.Utils
import com.livedata.sample.databinding.FirstActivityBinding
import com.livedata.sample.viewmodel.FirstViewModel

class FirstActivity : AppCompatActivity() {
    //Declaring variables
    private lateinit var binding: FirstActivityBinding
    lateinit var viewModel: FirstViewModel
    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    //initiating Adapter
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)
        binding = FirstActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiating FirstViewModel
        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        //Next button click listener
        binding.nextBt.setOnClickListener {
            val secondActivity = Intent(this, SecondActivity::class.java)
            startActivityForResult(secondActivity, SECOND_ACTIVITY_REQUEST_CODE)
        }
        //setting adpter into recycler view
        binding.recyclerview.adapter = adapter


        //this call back will get call when  live data is updated from viewmodel
        viewModel.userList.observe(this, Observer {
            adapter.setuserList(it)
            binding.progress.visibility = View.GONE
            Log.i("show first name", it[5].name + "")
        })

        //this call back will get call when the API is get failure
        viewModel.errorMessage.observe(this, Observer {
            Log.e("Error", it)

        })

        //Make the progress visibile before calling the API
        binding.progress.visibility = View.VISIBLE
        //Call API flow thorugh viewmodel
        viewModel.getAllUsers()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Get String data from Intent
                val isRefresh = data!!.getBooleanExtra("refreshed", false)
                if (isRefresh) {
                    binding.progress.visibility = View.VISIBLE
                    viewModel.getAllUsers()
                    Utils.changeFirstName(viewModel.userList)
                } else {
                    binding.progress.visibility = View.VISIBLE
                    viewModel.getAllUsers()
                }
            }
        }
    }
}