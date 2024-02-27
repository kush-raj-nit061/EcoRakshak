package com.ingray.ecorakshak.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.ingray.ecorakshak.Adapters.AdminMainAdapter
import com.ingray.ecorakshak.Adapters.UserRequestAdapter
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R

class AdminMainActivity : AppCompatActivity() {
    private lateinit var adapter:AdminMainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        val totalReqTextView: TextView = findViewById(R.id.totalreq)
        val reqResTextView: TextView = findViewById(R.id.reqres)
        val rv_Admin: RecyclerView = findViewById(R.id.rv_admin)

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
