package com.example.paymenttrackingapp.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.paymenttrackingapp.Controller.BLL.PaymentTypeBusinessLogic
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.databinding.ActivityPaymentDetailBinding

class PaymentDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentDetailBinding

    //lateinit var paymentType:PaymentType
    lateinit var PaymentTypeList:ArrayList<PaymentType>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)

        var paymentType = intent.getSerializableExtra("p_type") as PaymentType

        //paymentType=PaymentTypeList.get(positionq)


        binding.tvPaymentDetailPd.text=" ' ${paymentType.Title} '  tipindeki ödemeler"

        binding.btnUpdatePd.setOnClickListener{
            Toast.makeText(this,"Güncelleme yapılacak", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,NewPayTypeActivity::class.java)
            intent.putExtra("sitem",paymentType)
            resultLauncher.launch(intent)

        }

        binding.btnAddPaymentPd.setOnClickListener {
            Toast.makeText(this,"Ödeme Eklenecek", Toast.LENGTH_SHORT).show()
        }


    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::reResult )

    fun reResult(result: ActivityResult){
        if(result.resultCode== RESULT_OK){
            finish()

        }
        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this,"İşlemi iptel ettiniz", Toast.LENGTH_SHORT).show()
        }
    }


}