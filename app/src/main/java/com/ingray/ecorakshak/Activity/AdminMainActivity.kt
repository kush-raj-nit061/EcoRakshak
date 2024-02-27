package com.ingray.ecorakshak.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ingray.ecorakshak.Adapters.AdminMainAdapter
import com.ingray.ecorakshak.Adapters.UserRequestAdapter
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R
import java.util.Locale

class AdminMainActivity : AppCompatActivity() {
    private lateinit var adapter:AdminMainAdapter
    var noOfReq:Long = 0
    var reqResol:Long = 0
    val dbRef = FirebaseDatabase.getInstance().reference.child("Items")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        val totalReq: TextView = findViewById(R.id.totalreq)
        val reqRes: TextView = findViewById(R.id.reqres)
        val rv_Admin: RecyclerView = findViewById(R.id.rv_admin)

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    noOfReq= snapshot.childrenCount
                    totalReq.text = "Number of Requests : $noOfReq"
                    for (snaps in snapshot.children){
                        if (snapshot.child("status").getValue().toString()
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


        rv_Admin.itemAnimator = null

        val options: FirebaseRecyclerOptions<SentData?> =
            FirebaseRecyclerOptions.Builder<SentData>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Clubs"),
                    SentData::class.java
                )
                .build()
        adapter = AdminMainAdapter(options)

        rv_Admin.adapter = adapter
        adapter.startListening()

        // You can use these views as needed in your activity
    }
}
