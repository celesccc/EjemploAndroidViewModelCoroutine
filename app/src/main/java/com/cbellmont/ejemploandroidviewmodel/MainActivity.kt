package com.cbellmont.ejemploandroidviewmodel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cbellmont.ejemploandroidviewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model :MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        downloadAll()

        binding.button1.setOnClickListener {
            binding.tvFilms.text = ""
            binding.pbLoading.visibility = View.VISIBLE

            downloadBoton1()
        }

        binding.button2.setOnClickListener {
            binding.tvFilms.text = ""
            binding.pbLoading.visibility = View.VISIBLE

            CoroutineScope(IO).launch {
                val list = model.getBoton2()

                withContext (Main) {
                    model.getBoton2().forEach {
                        binding.tvFilms.append("${it.name}\n")
                    }
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }

        binding.button3.setOnClickListener {
            binding.tvFilms.text = ""
            binding.pbLoading.visibility = View.VISIBLE

            downloadBoton3()
        }
    }

    private fun downloadAll(){
        CoroutineScope(IO).launch {
            val list = model.getFilms()
            setTextOnMainThread(list)
        }
    }

    private fun downloadBoton1(){
        CoroutineScope(IO).launch {
            val list = model.getBoton1()
            setTextBoton1(list)
        }
    }

    private fun downloadBoton3(){
        CoroutineScope(IO).launch {
            val list = model.getBoton3()
            setTextBoton3(list)
        }
    }

    private suspend fun setTextOnMainThread(list: MutableList<Film>) {
        withContext (Main) {
            list.forEach {
                binding.tvFilms.append("${it.name}\n")
            }
            binding.pbLoading.visibility = View.GONE
        }
    }

    private suspend fun setTextBoton1(list: MutableList<Film>) {
        withContext (Main) {
            model.getBoton1().forEach {
                binding.tvFilms.append("${it.name}\n")
            }
            binding.pbLoading.visibility = View.GONE
        }
    }

    private suspend fun setTextBoton3(list: MutableList<Film>) {
        withContext (Main) {
            model.getBoton3().forEach {
                binding.tvFilms.append("${it.name}\n")
            }
            binding.pbLoading.visibility = View.GONE
        }
    }
}