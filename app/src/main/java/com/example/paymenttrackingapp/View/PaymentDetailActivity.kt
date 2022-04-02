package com.example.paymenttrackingapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paymenttrackingapp.databinding.ActivityPaymentDetailBinding

class PaymentDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityPaymentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}