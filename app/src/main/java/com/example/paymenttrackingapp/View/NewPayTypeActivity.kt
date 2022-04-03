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
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.databinding.ActivityNewPayTypeBinding

class NewPayTypeActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewPayTypeBinding

    var yearly="Yıllık"
    var monthly = "Aylık"
    var weekly= "Haftalık"

    var periodList= arrayOf(yearly,monthly,weekly)
    lateinit var day:String
    lateinit var title:String
    lateinit var paymentType:PaymentType
    var pt=PaymentType()

    lateinit var PaymentTypeList:ArrayList<PaymentType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewPayTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var isNew:Boolean

        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)

        isNew=intent.getBooleanExtra("new",false)

        if (isNew == true ){
            //is a new type
            newTypeSetter()
        }
        else{
            updatingTypeSetter()
        }

    }

    fun toastInvalidDay(){
        Toast.makeText(this,"Geçerli bir gün giriniz", Toast.LENGTH_SHORT).show()
    }


    fun newTypeSetter(){
        //delete button
        binding.btnDeleteNT.isVisible=false

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

            title= binding.eTvPayTypeTitleNT.text.toString()
            pt.Title=title
            day = binding.eTvDayNT.text.toString()
            pt.Day=day

            if (day == ""){
                toastInvalidDay()
            }
            else if (title==""){
                Toast.makeText(this,"Geçerli bir tip giriniz", Toast.LENGTH_SHORT).show()
            }
            else{
                /*if (pt.Period==yearly){
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
                }*/

                PaymentTypeBusinessLogic.addPaymentType(this,pt)
                setResult(RESULT_OK)
                finish()
            }
        }


    }

    fun updatingTypeSetter(){

        val position=intent.getIntExtra("p_type_item",0)
        val p:PaymentType=PaymentTypeList.get(position)
        binding.eTvPayTypeTitleNT.setText(p.Title)
        binding.eTvDayNT.setText(p.Day)



        binding.btnSaveTypeNT.setOnClickListener {
            PaymentTypeBusinessLogic.updatePaymentType(this,p)
            val intent=Intent() //for update and delete operations
            intent.putExtra("saveT_deleteF","save")
            setResult(RESULT_OK,intent)
            finish()
        }
        binding.btnDeleteNT.setOnClickListener {
            PaymentTypeBusinessLogic.deletePaymentType(this,p.Id)
            val intent=Intent() //for update and delete operations
            intent.putExtra("saveT_deleteF","delete")
            setResult(RESULT_OK,intent)
            finish()
        }

        //spinner
        val adapter = ArrayAdapter(this,
            R.layout.simple_spinner_item,periodList)
        binding.spinnerPeriodNT.adapter = adapter

        binding.spinnerPeriodNT.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                p.Period=periodList.get(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }

}