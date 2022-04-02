package com.example.paymenttrackingapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymenttrackingapp.Controller.BLL.PaymentTypeBusinessLogic
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.R
import com.example.paymenttrackingapp.View.PaymentType.PaymentTypesAdapter
import com.example.paymenttrackingapp.databinding.ActivityMainBinding
import com.example.paymenttrackingapp.databinding.ActivityNewPayTypeBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    lateinit var PaymentTypeList:ArrayList<PaymentType>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Status Bar Design
        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true


        getAllPaymentTypes()

        binding.btnNewPayType.setOnClickListener {

            val intent=Intent(this,NewPayTypeActivity::class.java)
            resultLauncher.launch(intent)
        }



    }


    fun itemClick(position:Int){
        Toast.makeText(this,"tıkladığınız eleman: "+PaymentTypeList.get(position),Toast.LENGTH_SHORT).show()

    }

    fun getAllPaymentTypes(){

        PaymentTypeList=PaymentTypeBusinessLogic.getAllPaymentTypes(this)

        val lm=LinearLayoutManager(this)
        lm.orientation=LinearLayoutManager.VERTICAL
        binding.rvpayType.layoutManager=lm
        binding.rvpayType.adapter=PaymentTypesAdapter(this,PaymentTypeList,::itemClick)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult() ){
            result->
        if(result.resultCode== RESULT_OK){
            getAllPaymentTypes()
            Toast.makeText(this,"Ödeme Tipiniz Kaydedilmiştir",Toast.LENGTH_SHORT).show()
        }

    }



}