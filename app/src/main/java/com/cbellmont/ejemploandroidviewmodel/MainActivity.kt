package com.cbellmont.ejemploandroidviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cbellmont.ejemploandroidviewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        model.downloadFilms()

        model.films.forEach {
            binding.tvFilms.append("${it.name}\n")
        }

        binding.button1.setOnClickListener {
            binding.tvFilms.text = ""

            model.getBoton1().forEach {
                binding.tvFilms.append("${it.name}\n")
            }
        }

        binding.button2.setOnClickListener {
            binding.tvFilms.text = ""

            model.getBoton2().forEach {
                binding.tvFilms.append("${it.name}\n")
            }
        }

        binding.button3.setOnClickListener {
            binding.tvFilms.text = ""

            model.getBoton3().forEach {
                binding.tvFilms.append("${it.name}\n")
            }
        }

    }
}