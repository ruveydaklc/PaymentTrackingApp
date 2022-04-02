package com.example.paymenttrackingapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paymenttrackingapp.databinding.ActivityNewPayTypeBinding

class NewPayTypeActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewPayTypeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewPayTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}