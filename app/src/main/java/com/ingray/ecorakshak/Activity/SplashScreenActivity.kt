package com.ingray.ecorakshak.Activity
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.ingray.ecorakshak.R

class SplashScreenActivity : AppCompatActivity() {
   private lateinit var btnSignin:Button
   private lateinit var btnSignup:Button
   private lateinit var rlsignup:RelativeLayout
   private lateinit var rllogin:RelativeLayout
    var dbRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        btnSignin = findViewById(R.id.login)
        btnSignup = findViewById(R.id.signup)
        rllogin = findViewById(R.id.rllogin)
        rlsignup = findViewById(R.id.rlsignup)
        btnSignin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
         var auth: FirebaseAuth = Firebase.auth
        if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == true){
            dbRef.child(auth.currentUser?.uid.toString()).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        if (snapshot.child("userType").value =="1"){
                            val intent = Intent(applicationContext,AdminMainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            val intent = Intent(applicationContext,MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }else{
            rlsignup.visibility = View.VISIBLE
            rllogin.visibility = View.VISIBLE
        }
    }
}