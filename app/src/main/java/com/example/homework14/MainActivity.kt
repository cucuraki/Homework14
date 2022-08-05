package com.example.homework14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework14.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: Adapter by lazy {
        Adapter()
    }
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    private val model:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.getInfo()
        initRecView()
        trigger()
    }
    private fun initRecView(){
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(this)
        adapter.submitList(model.state.value)
    }
    private fun trigger(){
        lifecycleScope.launch(defaultDispatcher){
            model.state.collect{
                adapter.submitList(it)
            }
        }
    }
}