package com.luizeduardobrandao.datastore.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizeduardobrandao.datastore.R
import com.luizeduardobrandao.datastore.databinding.ActivityUsernameBinding
import com.luizeduardobrandao.datastore.viewmodel.UsernameViewModel

class UsernameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsernameBinding
    private val viewModel: UsernameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.userNameLiveData.observe(this) { name ->
            binding.tvWelcome.text = "Bem vindo $name"
        }
    }
}