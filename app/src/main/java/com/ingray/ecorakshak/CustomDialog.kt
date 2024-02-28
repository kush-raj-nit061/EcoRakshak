package com.ingray.ecorakshak


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.ingray.ecorakshak.DataClass.SentData

class CustomDialog(private val context: Context, private val model: SentData, private val admin:Int) {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.custom_dialog, null)

        // Access dialog views and set model data
        val name = dialogView.findViewById<TextView>(R.id.name)
        val email = dialogView.findViewById<TextView>(R.id.email)
        val phone = dialogView.findViewById<TextView>(R.id.phone)
        val city = dialogView.findViewById<TextView>(R.id.city)
        val pincode = dialogView.findViewById<TextView>(R.id.pincode)
        val street = dialogView.findViewById<TextView>(R.id.street)
        val landmark = dialogView.findViewById<TextView>(R.id.landmark)
        val district = dialogView.findViewById<TextView>(R.id.district)
        val qtyKg = dialogView.findViewById<TextView>(R.id.qtyKg)
        val qtyNo = dialogView.findViewById<TextView>(R.id.qtyNo)
        val itemList = dialogView.findViewById<TextView>(R.id.itemList)
        val llAddress = dialogView.findViewById<LinearLayout>(R.id.llAddress)
        val ok = dialogView.findViewById<Button>(R.id.submit)
        if(admin==1){
            llAddress.visibility = View.VISIBLE
        }
        name.text = model.name
        email.text = model.email
        phone.text = model.phone
        city.text = model.city
        pincode.text = model.pincode
        street.text = model.streetName
        landmark.text = model.landmark
        district.text = model.district
        qtyKg.text = model.qtyKg + "KG"
        qtyNo.text = model.qtyNo + "Nos"
        itemList.text = model.itemType


        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
        ok.setOnClickListener{
            alertDialog.dismiss()
        }
    }
}