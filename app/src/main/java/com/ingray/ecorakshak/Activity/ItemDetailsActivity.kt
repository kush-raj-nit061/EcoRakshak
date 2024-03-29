package com.ingray.ecorakshak.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.DataClass.Users
import com.ingray.ecorakshak.R
import java.util.Calendar

class ItemDetailsActivity : AppCompatActivity() {
    private lateinit var home: Button
    private lateinit var qtyNo: EditText
    private lateinit var qtyKg: EditText
    private lateinit var itemList: Spinner
    private lateinit var district: EditText
    private lateinit var city: EditText
    private lateinit var street: EditText
    private lateinit var landmark: EditText
    private lateinit var pincode: EditText
    private lateinit var submit: Button
    private lateinit var svDetails: ScrollView
    private lateinit var svSubmitted: ScrollView
    private var itemType = ""
    private val dbRef:DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
    private val dbRefSet:DatabaseReference = FirebaseDatabase.getInstance().reference.child("Items")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        callViewById()
        val name: TextView = findViewById(R.id.name)
        dbRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val names:String = snapshot.child("name").value.toString()
                    name.text = names.subSequence(0,1)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        getItemFromSpinner()
        callOnClickListener()
    }
    private fun getItemFromSpinner() {
        val data= arrayOf("Mobile/Telephone","CFL/Tube Light","Clothing Iron","Computer/Laptop","Computer Accessories","Tablets/I-PAD",
            "Printer/Scanner","Camera/CCTV","Electric Fan","TV/Set top Boxes","Inverter","Refrigerator","Washing Machine","Air Conditioner","Radio Set/Sound Box",
            "Solar Panel","Microwave Oven","Induction Stove","Electric cooker","Kettle","Grinder","Electric Toys","Others")
        val adapt= ArrayAdapter(this,android.R.layout.simple_spinner_item,data)
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        itemList.adapter=adapt
        itemList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                itemType = parent.getItemAtPosition(position).toString()
                // Handle the selected item
                Toast.makeText(applicationContext,itemType,Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }
    }

    private fun callOnClickListener() {
        home.setOnClickListener{
            val intent = Intent(this,ItemDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
        submit.setOnClickListener{
            val items = SentData()

            dbRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val user: Users? = snapshot.getValue(Users::class.java)
                        items.itemType = itemType
                        items.qtyNo = qtyNo.text.toString()
                        items.qtyKg = qtyKg.text.toString()
                        items.landmark = landmark.text.toString()
                        items.city = city.text.toString()
                        items.pincode = pincode.text.toString()
                        items.district = district.text.toString()
                        items.streetName = street.text.toString()
                        items.name = user?.name.toString()
                        items.email = user?.email.toString()
                        items.phone = user?.phone.toString()
                        items.userId=user?.userId.toString()
                        items.status = "Waiting"
                        val time:String = Calendar.getInstance().timeInMillis.toString()
                        items.time = time
                        items.key= time

                        dbRefSet.child(time).setValue(items).addOnCompleteListener{
                            svDetails.visibility = View.GONE
                            svSubmitted.visibility = View.VISIBLE
                        }
                        dbRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Items").child(time).setValue(items)

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        }
    }

    private fun callViewById() {
        home = findViewById(R.id.home)
        qtyNo = findViewById(R.id.qtyNo)
        qtyKg = findViewById(R.id.qtyKg)
        itemList = findViewById(R.id.itemList)
        district = findViewById(R.id.district)
        city = findViewById(R.id.city)
        street = findViewById(R.id.street)
        landmark = findViewById(R.id.landmark)
        pincode = findViewById(R.id.pincode)
        submit = findViewById(R.id.submit)
        svDetails = findViewById(R.id.svDetails)
        svSubmitted = findViewById(R.id.svSubmitted)
    }
}
