package com.luizeduardobrandao.datastore.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizeduardobrandao.datastore.R
import com.luizeduardobrandao.datastore.databinding.ActivityMainBinding
import com.luizeduardobrandao.datastore.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
    }

    private fun setListeners() {

        binding.btnNext.setOnClickListener {
            val name = binding.etUserName.text.toString().trim()

            if (name.isNotEmpty()) {
                viewModel.saveUserName(name)
                startActivity(Intent(this, UsernameActivity::class.java))
            }
            else {
                Toast.makeText(
                    this, "Informe um nome", Toast.LENGTH_LONG).show()
            }
        }
    }
}