package com.ingray.ecorakshak.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.ingray.ecorakshak.Adapters.MainAdapter
import com.ingray.ecorakshak.Adapters.UserRequestAdapter
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val rv_user: RecyclerView = findViewById(R.id.rv_user)
        val myReq: Button = findViewById(R.id.myreq)
        val makeReq: Button = findViewById(R.id.makereq)
        val totalReq: TextView = findViewById(R.id.totalreq)
        val reqRes: TextView = findViewById(R.id.reqres)
        rv_user.itemAnimator = null

        val options: FirebaseRecyclerOptions<SentData?> =
            FirebaseRecyclerOptions.Builder<SentData>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Clubs"),
                    SentData::class.java
                )
                .build()
        adapter = MainAdapter(options)

        rv_user.adapter = adapter
        adapter.startListening()
    }
}
