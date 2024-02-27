package com.ingray.ecorakshak.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

class LoginActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var signup:TextView
    private lateinit var reset:TextView
    private lateinit var signin:Button
    private lateinit var auth: FirebaseAuth
    lateinit var sEmail:String
    lateinit var sPass:String
     var dbRef:DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        callVariablesById()
        val tent = intent
        val cEmail = tent.getStringExtra("email").toString()
        if(cEmail!="null"){
            email.setText(cEmail)
        }

        callOnClickListeners()


        // You can use these views as needed in your activity
    }

    private fun callOnClickListeners() {



        signin.setOnClickListener{
            sEmail = email.text.toString()
            sPass = password.text.toString()
            var b:Boolean = checkTheConditions()
            if(b){
                auth.signInWithEmailAndPassword(sEmail, sPass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            if(auth.currentUser?.isEmailVerified == true){
                                 dbRef.child(auth.currentUser.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        if (snapshot.exists()){
                                            if (snapshot.child("userType").getValue()=="1"){
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
                                Toast.makeText(
                                    applicationContext,
                                    "Check your email for verification link",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }
        reset.setOnClickListener{
            var intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener{
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkTheConditions():Boolean {
        if (TextUtils.isEmpty(email.text.toString())) {
            Toast.makeText(
                applicationContext,
                "Please enter email!!",
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }

        if (TextUtils.isEmpty(password.text.toString())) {
            Toast.makeText(
                applicationContext,
                "Please enter password!!",
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        return true
    }


    private fun callVariablesById() {
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPass)
        reset = findViewById(R.id.tvForget)
        signup = findViewById(R.id.tvSignup)
        signin= findViewById(R.id.login)
        auth = Firebase.auth
    }
}
