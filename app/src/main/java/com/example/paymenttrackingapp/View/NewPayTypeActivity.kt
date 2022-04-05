package com.example.paymenttrackingapp.View

import android.R
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.core.view.isVisible
import com.example.paymenttrackingapp.Controller.BLL.PaymentBusinessLogic
import com.example.paymenttrackingapp.Controller.BLL.PaymentTypeBusinessLogic
import com.example.paymenttrackingapp.Model.Payment
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
    var id:Int?=null
    var pt=PaymentType(id)
    lateinit var PaymentTypeList:ArrayList<PaymentType>
    lateinit var p: PaymentType



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewPayTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        day=""
        title=""

        var isNew:Boolean

        PaymentTypeList= PaymentTypeBusinessLogic.getAllPaymentTypes(this)

        isNew=intent.getBooleanExtra("new",false) //from MainActivity - is a new record

        if (isNew == true ){
            //is a new type
            newTypeSetter()
        }
        else{
            updatingTypeSetter()
        }

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
                Toast.makeText(this,"Geçerli bir başlık giriniz", Toast.LENGTH_SHORT).show()
            }
            else{
                dateAddOrganize(pt)
            }
        }


    }

    fun updatingTypeSetter(){
        p= intent.getSerializableExtra("sitem") as PaymentType  //from PaymentDetailActivity - updating type item
        binding.eTvPayTypeTitleNT.setText(p.Title)
        binding.eTvDayNT.setText(p.Day)

        binding.btnSaveTypeNT.setOnClickListener {
            dateUpdateOrganize(p)
        }
        binding.btnDeleteNT.setOnClickListener {
            showPopupDialog(p)
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
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }


    fun toastInvalidDay(){
        Toast.makeText(this,"Geçerli bir gün giriniz", Toast.LENGTH_SHORT).show()
    }

    fun dateAddOrganize(pt:PaymentType){
        if (day.toInt() <= 0){
            toastInvalidDay()
        }
        else{
            if (pt.Period == yearly){
                if (day.toInt() > 365 ){
                    toastInvalidDay()
                }
                else{
                    elseaddPayFun(pt)
                }

            }
            else if (pt.Period==monthly){
                if (day.toInt()>31){
                    toastInvalidDay()
                }
                else{
                    elseaddPayFun(pt)
                }
            }
            else if (pt.Period==weekly){
                if (day.toInt()>7){
                    toastInvalidDay()
                }
                else{
                    elseaddPayFun(pt)
                }
            }
        }
    }

    fun elseaddPayFun(pt:PaymentType){
        var value:String?=null
        var pTValue=PaymentType(id)

        for (i in PaymentTypeList){
            if (binding.eTvPayTypeTitleNT.text.toString() == i.Title)
            {
                value=i.Title
                pTValue.Id=i.Id

                pTValue.Title=binding.eTvPayTypeTitleNT.text.toString()
                pTValue.Day= binding.eTvDayNT.text.toString()
                pTValue.Period=spinnerFun()

            }
        }
        if (value != null ) //updating
        {
            PaymentTypeBusinessLogic.updatePaymentType(this,pTValue)
            /*val intent =Intent()
            intent.putExtra("upd",pTValue) // to mainactivity
            setResult(RESULT_OK,intent)*/

        }
        else {
            PaymentTypeBusinessLogic.addPaymentType(this,pt)

        }

        setResult(RESULT_OK)
        finish()

    }


    fun dateUpdateOrganize(p: PaymentType){

        day = binding.eTvDayNT.text.toString()
        p.Title= binding.eTvPayTypeTitleNT.text.toString()
        p.Day=binding.eTvDayNT.text.toString()

        if (day.toInt() <= 0){
            toastInvalidDay()
        }
        else{
            if (p.Period==yearly){
                if (day.toInt() > 365){
                    toastInvalidDay()
                }
                else{
                    elseupdatePayFun(p)
                }

            }
            else if (p.Period==monthly){
                if (day.toInt()>31){
                    toastInvalidDay()
                }
                else{
                    elseupdatePayFun(p)
                }
            }
            else if (p.Period==weekly){
                if (day.toInt()>7){
                    toastInvalidDay()
                }
                else{
                    elseupdatePayFun(p)
                }
            }
        }



    }
    fun elseupdatePayFun(p:PaymentType){
        PaymentTypeBusinessLogic.updatePaymentType(this,p)

        val intent= Intent() //for update and delete operations
        //intent.putExtra("saveT_deleteF","save")     //to PaymentDetailActivity -to know is saving or deleting
        intent.putExtra("page_back","detail")   //to PaymentDetailActivity -to know which page to return to (detail or main)
        intent.putExtra("saved_item",p)     //to PaymentDetailActivity -to know saved item
        intent.putExtra("update_info","update")     //to PaymentDetailActivity -to know is updating
        setResult(RESULT_OK,intent)
        finish()
    }

    private fun showPopupDialog(p: PaymentType) {
        var popTitle="!!Uyarı!!"
        val popMessage="Ödemeyi silmek istiyor musunuz?"
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(com.example.paymenttrackingapp.R.layout.popup)
        val message = dialog.findViewById(com.example.paymenttrackingapp.R.id.tvPopupMessage) as TextView
        message.text = popMessage
        val title = dialog.findViewById(com.example.paymenttrackingapp.R.id.tvPopupTitle) as TextView
        title.text = popTitle
        val yesBtn = dialog.findViewById(com.example.paymenttrackingapp.R.id.btnDeletePopup) as Button
        val cancelBtn = dialog.findViewById(com.example.paymenttrackingapp.R.id.btnCancelPopup) as TextView
        yesBtn.setOnClickListener {
            PaymentTypeBusinessLogic.deletePaymentType(this, p.Id!!)
            val intent= Intent() //for update and delete operations
            //intent.putExtra("saveT_deleteF","delete")  //to know is saving or deleting
            intent.putExtra("page_back","main") //to know which page to return to (detail or main)
            intent.putExtra("update_info","delete") //to PaymentDetailActivity -to know is saving or deleting and deleting all payments
            setResult(RESULT_OK,intent)
            dialog.dismiss()
            finish()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }


    fun spinnerFun() :String{
        var a=""
        //spinner
        val adapter = ArrayAdapter(this,
            R.layout.simple_spinner_item,periodList)
        binding.spinnerPeriodNT.adapter = adapter

        binding.spinnerPeriodNT.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                a=periodList.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        return a
    }


}