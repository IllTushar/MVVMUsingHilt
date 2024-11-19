package com.example.implementdaggerhiltapi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.implementdaggerhiltapi.adapter.rec_adapter
import com.example.implementdaggerhiltapi.databinding.ActivityMainBinding
import com.example.implementdaggerhiltapi.model.ObjectsResponse
import com.example.implementdaggerhiltapi.model.ObjectsResponseItem
import com.example.implementdaggerhiltapi.viewmodel.ObjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: ObjectViewModel
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: rec_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[ObjectViewModel::class.java]

        mainViewModel.data.observe(this, Observer { data ->
            updateData(data)
        })

        mainViewModel.fetchData()
    }

    private fun updateData(data: ObjectsResponse?) {
        if (data != null) {
            val dataList = arrayListOf<ObjectsResponseItem>()
            for (item in data) {
                dataList.add(item)
            }
            adapter.setData(dataList)
            binding.recView.adapter = adapter

        } else {
            Log.d("MainScreen", "Data is null")
        }
    }
}