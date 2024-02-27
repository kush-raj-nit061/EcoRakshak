package com.ingray.ecorakshak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ingray.ecorakshak.R

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        val totalReqTextView: TextView = findViewById(R.id.totalreq)
        val reqResTextView: TextView = findViewById(R.id.reqres)
        val recyclerView: RecyclerView = findViewById(R.id.rv_admin)

        // You can use these views as needed in your activity
    }
}
