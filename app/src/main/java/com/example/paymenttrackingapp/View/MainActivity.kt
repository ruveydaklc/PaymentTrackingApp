package com.example.paymenttrackingapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paymenttrackingapp.R
import com.example.paymenttrackingapp.databinding.ActivityMainBinding
import com.example.paymenttrackingapp.databinding.ActivityNewPayTypeBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}