package com.example.paymenttrackingapp.View

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.databinding.ActivityAddPayBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class AddPayActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddPayBinding
    lateinit var payment:Payment

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pType:PaymentType=intent.getSerializableExtra("type") as PaymentType
        binding.tvDescPi.text="${pType.Title} türüne ödeme ekleyiniz"

        dateFun()



        binding.btnSavePi.setOnClickListener {
            payment.Price=binding.eTvPricePi.text.toString().toInt()


        }
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFun(){
        binding.btnCalendarPi.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"Datepicker")

            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter=SimpleDateFormat("dd-MM-yyyy")
                val date=dateFormatter.format(Date(it))
                Toast.makeText(this, "$date is selected", Toast.LENGTH_LONG).show()
                binding.tvDatePi.setText(date)
            }

            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()

        }
        }
    }
}