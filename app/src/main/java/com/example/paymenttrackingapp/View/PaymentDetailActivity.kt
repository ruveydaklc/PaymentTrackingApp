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
    lateinit var PaymentTypeList:ArrayList<PaymentType>
    lateinit var paymentList:ArrayList<Payment>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getLastPayments()
        paymentType = intent.getSerializableExtra("p_type") as PaymentType //from MainActivity - clicked type item


        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)
        paymentList=PaymentBusinessLogic.getAllPayments(this)

        binding.tvPaymentDetailPd.text=" ' ${paymentType.Title} '  tipindeki ödemeler"
        clickFun()


    }
    fun getLastPayments(){
        paymentList=PaymentBusinessLogic.getAllPayments(this)
        val lm =LinearLayoutManager(this)
        lm.orientation=LinearLayoutManager.VERTICAL
        binding.rvLastPayment.layoutManager=lm

        binding.rvLastPayment.adapter=LastPaymentAdapter(this,paymentList,::itemClick)
    }

    fun itemClick(position:Int){

        var pmnt:Payment =paymentList.get(position)
        showDialog(pmnt)

    }


    private fun showDialog(p:Payment) {
        var popTitle="Ödemeyi silmek istiyor musunuz?"
        val popMessage="Veriyi ödeme geçmişinden silmek için sil butonuna tıklayınız"
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
            Toast.makeText(this,"ürün sil",Toast.LENGTH_SHORT).show()
            PaymentBusinessLogic.deletePayment(this, p.Id!!)
            getLastPayments()
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

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
            intent.putExtra("type",paymentType) //to AddPayActivity
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
                if (result.data!!.getStringExtra("update_info") == "update"){
                    paymentType=result.data!!.getSerializableExtra("saved_item") as PaymentType
                    PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)
                    binding.tvPaymentDetailPd.text=" ' ${paymentType.Title} '  tipindeki ödemeler"
                    Toast.makeText(this,"İşlem başarılı.",Toast.LENGTH_SHORT).show()
                }
                else if (result.data!!.getStringExtra("update_info") == "not"){
                    getLastPayments()
                    Toast.makeText(this,"Ödeme Eklendi başarılı.",Toast.LENGTH_SHORT).show()
                    binding.rvLastPayment.adapter!!.notifyDataSetChanged()
                }
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