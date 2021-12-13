package com.livedata.sample.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livedata.sample.R
import com.livedata.sample.Utils
import com.livedata.sample.databinding.SecondActivityBinding
import com.livedata.sample.viewmodel.SecondViewModel

class SecondActivity : AppCompatActivity() {
    //Declaring variables
    private lateinit var binding: SecondActivityBinding
    lateinit var viewModel: SecondViewModel
    var isRefreshed: Boolean = false


    //initiating Adapter
    val adapter = SecondAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiating SecondViewModel
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        //Next button click listener
        binding.backBt.setOnClickListener {
            val intent = Intent()
            intent.putExtra("refreshed", isRefreshed)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        //Refresh button click listener
        binding.refreshBt.setOnClickListener {
            binding.progress.visibility = View.VISIBLE

            //Call API flow thorugh viewmodel
            viewModel.getAllUsers()
            Utils.changeFirstName(viewModel.userList)
            isRefreshed = true
        }
        //setting adpter into recycler view
        binding.recyclerview.adapter = adapter


        //this call back will get call when  live data is updated from viewmodel
        viewModel.userList.observe(this, Observer {
            adapter.setuserList(it)
            binding.progress.visibility = View.GONE
        })

        //this call back will get call when the API is get failure
        viewModel.errorMessage.observe(this, Observer {

        })

        //Make the progress visibile before calling the API
        binding.progress.visibility = View.VISIBLE
        //Call API flow thorugh viewmodel
        viewModel.getAllUsers()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        intent.putExtra("refreshed", isRefreshed)
        setResult(Activity.RESULT_OK, intent)
    }

}