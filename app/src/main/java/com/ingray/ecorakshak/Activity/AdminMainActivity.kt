package com.ingray.ecorakshak.Activity
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ingray.ecorakshak.Adapters.AdminMainAdapter
import com.ingray.ecorakshak.DataClass.SentData

import com.ingray.ecorakshak.R
import java.util.Locale

class AdminMainActivity : AppCompatActivity() {
    private lateinit var adapter: AdminMainAdapter
    var noOfReq:Long = 0
    var reqResol:Long = 0
    val dbRef = FirebaseDatabase.getInstance().reference.child("Items")
    val userRef = FirebaseDatabase.getInstance().reference.child("Users")
    private lateinit var rv_Admin:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        val totalReq: TextView = findViewById(R.id.totalreq)
        val reqRes: TextView = findViewById(R.id.reqres)
        rv_Admin = findViewById(R.id.rv_admin)
        rv_Admin.itemAnimator = null
        val name:TextView = findViewById(R.id.name)

        userRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
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

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    noOfReq= snapshot.childrenCount
                    totalReq.text = "Number of Requests : $noOfReq"
                    for (snaps in snapshot.children){
                        if (snaps.child("status").getValue().toString()
                                .lowercase(Locale.ROOT) =="done"){
                            reqResol +=1;
                        }
                    }
                    reqRes.text = "Request resolved : $reqResol"
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })




        val options: FirebaseRecyclerOptions<SentData?> =
            FirebaseRecyclerOptions.Builder<SentData>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Items"),
                    SentData::class.java
                )
                .build()
        adapter = AdminMainAdapter(options)
        rv_Admin.adapter = adapter
        adapter.startListening()

        // You can use these views as needed in your activity
    }
}
