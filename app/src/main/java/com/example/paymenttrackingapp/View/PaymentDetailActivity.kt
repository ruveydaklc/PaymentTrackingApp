package com.example.paymenttrackingapp.View

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.Gravity
import android.view.Window
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.hardware.display.DisplayManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymenttrackingapp.Controller.BLL.PaymentBusinessLogic
import com.example.paymenttrackingapp.Controller.BLL.PaymentTypeBusinessLogic
import com.example.paymenttrackingapp.Model.Payment
import com.example.paymenttrackingapp.Model.PaymentType
import com.example.paymenttrackingapp.R
import com.example.paymenttrackingapp.View.LastPayments.LastPaymentAdapter
import com.example.paymenttrackingapp.databinding.ActivityPaymentDetailBinding
import com.example.paymenttrackingapp.databinding.PopupBinding

class PaymentDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentDetailBinding

    lateinit var paymentType:PaymentType
    lateinit var paymentTypeResult:PaymentType
    lateinit var PaymentTypeList:ArrayList<PaymentType>
    lateinit var paymentList:ArrayList<Payment>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        paymentType = intent.getSerializableExtra("p_type") as PaymentType //from MainActivity - clicked type item

        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)
        paymentList=PaymentBusinessLogic.getAllPayments(this,paymentType.Title)

        getLastPayments(paymentType.Title)

        binding.tvPaymentDetailPd.text = " ' ${paymentType.Title} '  tipindeki ödemeler"
        clickFun()


    }
    fun getLastPayments(ptype:String){
        paymentList=PaymentBusinessLogic.getAllPayments(this,ptype)

        val lm =LinearLayoutManager(this)
        lm.orientation=LinearLayoutManager.VERTICAL
        binding.rvLastPayment.layoutManager=lm

        binding.rvLastPayment.adapter=LastPaymentAdapter(this,paymentList,::itemClick)
    }

    fun itemClick(position:Int) //payment list
    {
        var pmnt:Payment =paymentList.get(position)
        showDialog(pmnt)
    }


    private fun showDialog(p:Payment) {
        var popTitle="!!Uyarı!!"
        val popMessage="Ödemeyi silmek istiyor musunuz?"
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.popup)
        val message = dialog.findViewById(R.id.tvPopupMessage) as TextView
        message.text = popMessage
        val title = dialog.findViewById(R.id.tvPopupTitle) as TextView
        title.text = popTitle
        val yesBtn = dialog.findViewById(R.id.btnDeletePopup) as Button
        val cancelBtn = dialog.findViewById(R.id.btnCancelPopup) as TextView
        yesBtn.setOnClickListener {
            PaymentBusinessLogic.deletePayment(this, p.Id!!)
            getLastPayments(paymentType.Title)
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun clickFun(){
        binding.btnUpdatePd.setOnClickListener{
            val intent = Intent(this,NewPayTypeActivity::class.java)
            intent.putExtra("sitem",paymentType) //to NewPayTypeActivity - updating type item
            resultLauncher.launch(intent)
        }

        binding.btnAddPaymentPd.setOnClickListener {
            val intent= Intent(this,AddPayActivity::class.java)
            intent.putExtra("type",paymentType) //to AddPayActivity
            resultLauncher.launch(intent)

        }

    }



    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::reResult )

    @SuppressLint("SetTextI18n")
    fun reResult(result: ActivityResult){
        if(result.resultCode== RESULT_OK){
            var info_updateOrNot =result.data!!.getStringExtra("update_info") //from NewPayTypeActivity and AddPayActivity -to know is update or not

            val intentValue:String= result.data!!.getStringExtra("page_back").toString() //from AddPayActivity and NewPaymentActivity -to know which page to return to (detail or main)

            if (intentValue == "detail") //to know which page to return to (detail or main)
            {
                if (info_updateOrNot == "update") //from NewPayTypeActivity -to know is update or not
                {

                    paymentTypeResult=result.data!!.getSerializableExtra("saved_item") as PaymentType //from NewPayTypeActivity -to know saved item
                    paymentType=paymentTypeResult
                    PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)
                   // getLastPayments(paymentTypeResult.Title,)
                    binding.tvPaymentDetailPd.text=" ' ${paymentTypeResult.Title} '  tipindeki ödemeler"
                    Toast.makeText(this,"İşlem başarılı.",Toast.LENGTH_SHORT).show()

                    //to update payment list
                    for (i in paymentList){
                        PaymentBusinessLogic.updateToPatmentType(this,i,paymentTypeResult.Title)
                    }
                    getLastPayments(paymentTypeResult.Title)

                }
                else if (info_updateOrNot == "not") //from AddPayActivity -to know is update or not
                {
                    getLastPayments(paymentType.Title)
                    Toast.makeText(this,"Ödeme Eklendi başarılı.",Toast.LENGTH_SHORT).show()
                    binding.rvLastPayment.adapter!!.notifyDataSetChanged()
                }
            }
            else if (intentValue== "main") //from NewPayTypeActivity -to know which page to return to (detail or main)
            {

                if (info_updateOrNot=="delete") //from NewPAyTypeActivity -to deleting all payments
                {
                    for (i in paymentList){
                    PaymentBusinessLogic.deletePayment(this, i.Id!!)
                    Toast.makeText(this,"Ödeme tipiyle birlikte ödeme geçmişi de silinmiştir.",Toast.LENGTH_SHORT).show()
                    }
                }
                setResult(RESULT_OK)
                finish()
            }

        }

        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this,"İşlemi iptel ettiniz", Toast.LENGTH_SHORT).show()
        }
    }


}