package com.ingray.ecorakshak
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ingray.ecorakshak.R

class UserRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_request)
        val totalreq: TextView = findViewById(R.id.totalreq)
        val reqres: TextView = findViewById(R.id.reqres)
        val rv_user_req: RecyclerView = findViewById(R.id.rv_user_req)
    }
}