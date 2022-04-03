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
    lateinit var binding:ActivityPaymentDetailBinding

    lateinit var paymentType:PaymentType
    lateinit var PaymentTypeList:ArrayList<PaymentType>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)

        var positionq = intent.getIntExtra("p_type",0) as Int

        paymentType=PaymentTypeList.get(positionq)


        binding.tvPaymentDetailPd.text=" ' ${paymentType.Title} '  tipindeki ödemeler"

        binding.btnUpdatePd.setOnClickListener{
            Toast.makeText(this,"Güncelleme yapılacak",Toast.LENGTH_SHORT).show()
            val intent =Intent(this,NewPayTypeActivity::class.java)
            intent.putExtra("p_type_item",positionq)
            resultLauncher.launch(intent)

        }

        binding.btnAddPaymentPd.setOnClickListener {
            Toast.makeText(this,"Ödeme Eklenecek",Toast.LENGTH_SHORT).show()
        }


    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::reResult )

    fun reResult(result: ActivityResult){
        if(result.resultCode== RESULT_OK){
            if (intent.getStringExtra("saveT_deleteF").equals("save") ){
                //saving operation
            }
            else if (intent.getStringExtra("saveT_deleteF").equals("delete")){
                //deleting operation
                finish()
            }

        }
        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this,"İşlemi iptel ettiniz",Toast.LENGTH_SHORT).show()
        }
    }


}