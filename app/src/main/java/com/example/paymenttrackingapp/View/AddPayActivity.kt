package com.example.paymenttrackingapp.View

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.R
import com.example.paymenttrackingapp.databinding.ActivityAddPayBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class AddPayActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddPayBinding

    var id:Int?=null
    var price:Int?=null

    var payment = Payment(id, price)


    private var year = 0
    private var month = 0
    private var day = 0
    private lateinit var calendar: Calendar


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pType:PaymentType=intent.getSerializableExtra("type") as PaymentType
        binding.tvDescPi.text="${pType.Title} türüne ödeme ekleyiniz"

        binding.btnCalendarPi.setOnClickListener {
            dateFun()
        }

        binding.btnSavePi.setOnClickListener {
            payment.Price=binding.eTvPricePi.text.toString().toInt()
            payment.Year_date=year
            payment.Month_date=month
            payment.Day_date=day
            val intent=Intent()
            intent.putExtra("page_back","detail")
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    @SuppressLint("SimpleDateFormat", "ResourceAsColor")
    fun dateFun(){
        calendar= Calendar.getInstance()

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(this, { _, year, month, day_of_month ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month + 1
                calendar[Calendar.DAY_OF_MONTH] = day_of_month
                val dateFormatter = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(dateFormatter, Locale.getDefault())
                binding.tvDatePi.text = sdf.format(calendar.time)
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
        calendar.add(Calendar.YEAR, 0)

        //max and min date is today
        dialog.datePicker.minDate = GregorianCalendar(year - 50, month, day, 0, 0).timeInMillis
        dialog.datePicker.maxDate = calendar.timeInMillis
        dialog.show()

    }

}