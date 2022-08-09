package com.example.homework14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val model: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecView()
        trigger()
        setOnRefreshListener()

    }

    private fun initRecView() {
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(this)

    }

    private fun trigger() {

        lifecycleScope.launch(mainDispatcher) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.flow.collect {
                    when (it) {
                        is Resource.Load -> {
                            Toast.makeText(applicationContext, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success -> {
                            adapter.submitList(it.body)
                        }
                        is Resource.Error -> {
                            Toast.makeText(applicationContext, it.errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                binding.refresh.isRefreshing = false
            }
        }

    }

    private fun setOnRefreshListener() {
        binding.refresh.setOnRefreshListener {
            trigger()
        }
    }
}