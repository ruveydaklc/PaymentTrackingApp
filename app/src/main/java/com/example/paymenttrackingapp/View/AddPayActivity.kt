package com.example.paymenttrackingapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paymenttrackingapp.databinding.ActivityAddPayBinding
import com.example.paymenttrackingapp.databinding.ActivityMainBinding

class AddPayActivity : AppCompatActivity() {
    lateinit var binding :ActivityAddPayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}