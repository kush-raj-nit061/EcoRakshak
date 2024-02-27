package com.ingray.ecorakshak.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.ingray.ecorakshak.Adapters.UserRequestAdapter
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R

class UserRequestActivity : AppCompatActivity() {
    private lateinit var adapter:UserRequestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_request)
        val totalreq: TextView = findViewById(R.id.totalreq)
        val reqres: TextView = findViewById(R.id.reqres)
        val rv_user_req: RecyclerView = findViewById(R.id.rv_user_req)
        rv_user_req.itemAnimator = null

        val options: FirebaseRecyclerOptions<SentData?> =
            FirebaseRecyclerOptions.Builder<SentData>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Clubs"),
                    SentData::class.java
                )
                .build()
        adapter = UserRequestAdapter(options)

        rv_user_req.adapter = adapter
        adapter.startListening()
    }
}