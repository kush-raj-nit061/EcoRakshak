package com.ingray.ecorakshak.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ingray.ecorakshak.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val rv_user: RecyclerView = findViewById(R.id.rv_user)
        val myReq: Button = findViewById(R.id.myreq)
        val makeReq: Button = findViewById(R.id.makereq)
        val totalReq: TextView = findViewById(R.id.totalreq)
        val reqRes: TextView = findViewById(R.id.reqres)

        // You can use these views as needed in your activity
    }
}
