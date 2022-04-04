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
import java.text.FieldPosition

class PaymentDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentDetailBinding

    lateinit var paymentType:PaymentType
    lateinit var PaymentTypeList:ArrayList<PaymentType>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        paymentType = intent.getSerializableExtra("p_type") as PaymentType

        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)

        binding.tvPaymentDetailPd.text=" ' ${paymentType.Title} '  tipindeki ödemeler"
        clickFun()



    }

    fun itemClick(position:Int){


    }


    fun clickFun(){
        binding.btnUpdatePd.setOnClickListener{
            Toast.makeText(this,"Güncelleme yapılacak", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,NewPayTypeActivity::class.java)
            intent.putExtra("sitem",paymentType)
            resultLauncher.launch(intent)

        }

        binding.btnAddPaymentPd.setOnClickListener {
            Toast.makeText(this,"Ödeme Eklenecek", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,AddPayActivity::class.java)
            intent.putExtra("type",paymentType)
            resultLauncher.launch(intent)

        }

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::reResult )

    @SuppressLint("SetTextI18n")
    fun reResult(result: ActivityResult){
        if(result.resultCode== RESULT_OK){
            val intentValue:String= result.data!!.getStringExtra("page_back").toString()

            if (intentValue == "detail") //if we want to back to the detail activity
            {
                paymentType=result.data!!.getSerializableExtra("saved_item") as PaymentType
                PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)
                binding.tvPaymentDetailPd.text=" ' ${paymentType.Title} '  tipindeki ödemeler"
                Toast.makeText(this,"İşlem başarılı.",Toast.LENGTH_SHORT).show()
            }
            else if (intentValue== "main") //if we want to back to the main activity
            {
                setResult(RESULT_OK)
                finish()
            }

        }
        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this,"İşlemi iptel ettiniz", Toast.LENGTH_SHORT).show()
        }
    }


}