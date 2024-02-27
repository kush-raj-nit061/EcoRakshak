package com.ingray.ecorakshak.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.ingray.ecorakshak.Adapters.MainAdapter
import com.ingray.ecorakshak.Adapters.UserRequestAdapter
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var noOfReq:Long = 0
    var reqResol:Long = 0
    val dbRef = FirebaseDatabase.getInstance().reference.child("Items")
    val userRef = FirebaseDatabase.getInstance().reference.child("Users")

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
        val name:TextView = findViewById(R.id.name)
        rv_user.itemAnimator = null

        makeReq.setOnClickListener{
            val intent = Intent(this,ItemDetailsActivity::class.java)
            startActivity(intent)
        }
        myReq.setOnClickListener{
            val intent = Intent(this,UserRequestActivity::class.java)
            startActivity(intent)
        }
        dbRef.addListenerForSingleValueEvent(object :ValueEventListener{
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
