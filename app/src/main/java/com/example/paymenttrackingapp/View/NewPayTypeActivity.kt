package com.example.paymenttrackingapp.View

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.paymenttrackingapp.Controller.BLL.PaymentTypeBusinessLogic
import com.example.paymenttrackingapp.Controller.PaymentTypeOperation
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.databinding.ActivityNewPayTypeBinding

class NewPayTypeActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewPayTypeBinding
    var yearly="Yıllık"
    var monthly = "Aylık"
    var weekly= "Haftalık"

    var periodList= arrayOf(yearly,monthly,weekly)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewPayTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pt=PaymentType()

        var day:String
        var title:String

        //adding new type
        binding.btnDeleteNT.isVisible=false


        title= binding.eTvPayTypeTitleNT.text.toString()
        pt.Title=title
        day = binding.eTvDayNT.text.toString()
        pt.Day=day


        //spinner
        val adapter = ArrayAdapter(this,
            R.layout.simple_spinner_item,periodList)
        binding.spinnerPeriodNT.adapter = adapter

        binding.spinnerPeriodNT.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //Toast.makeText(this@NewPayTypeActivity,periodList.get(position) + " seçildi" , Toast.LENGTH_SHORT).show()
                pt.Period=periodList.get(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



        binding.btnSaveTypeNT.setOnClickListener {

            //TODO(if else error)
            if (day == ""){
                toastInvalidDay()
            }
            else if (title==""){
                Toast.makeText(this,"Geçerli bir tip giriniz", Toast.LENGTH_SHORT).show()
            }
            else{
                if (pt.Period==yearly){
                    if (day.toInt() > 365){
                        toastInvalidDay()
                    }

                }
                else if (pt.Period==monthly){
                    if (day.toInt()>31){
                        toastInvalidDay()
                    }
                }
                else if (pt.Period==weekly){
                    if (day.toInt()>7){
                        toastInvalidDay()
                    }
                }
                else{
                    PaymentTypeBusinessLogic.addPaymentType(this,pt)
                    setResult(RESULT_OK)
                    finish()
                }

            }
        }


    }

    fun toastInvalidDay(){
        Toast.makeText(this,"Geçerli bir gün giriniz", Toast.LENGTH_SHORT).show()
    }

}