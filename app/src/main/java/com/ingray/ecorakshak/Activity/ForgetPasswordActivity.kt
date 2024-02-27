package com.ingray.ecorakshak.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ingray.ecorakshak.R

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var btnReset:Button
    private lateinit var sEmail:String
    private lateinit var autth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        email = findViewById(R.id.etEmail)
        btnReset = findViewById(R.id.btnSendLink)
        autth = FirebaseAuth.getInstance()

        btnReset.setOnClickListener{
            sEmail = email.text.toString()
            if(!sEmail.contains("nitp.ac.in")){
                Toast.makeText(applicationContext,"Invalid Email Format",Toast.LENGTH_SHORT).show()

            }else{
                autth.sendPasswordResetEmail(sEmail).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(applicationContext,"Email Sent Sucessfully",Toast.LENGTH_SHORT).show()
                        var i = Intent(this,LoginActivity::class.java)
                        i.putExtra("email",sEmail)
                        startActivity(i)
                        finish()

                    }else{
                        Toast.makeText(applicationContext,"Can't send email!!",Toast.LENGTH_SHORT).show()
                    }


                }

            }
        }

    }
}