package com.ingray.ecorakshak
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ingray.ecorakshak.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize views
        val etName: EditText = findViewById(R.id.etName)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPhone: EditText = findViewById(R.id.etPhone)
        val etPass: EditText = findViewById(R.id.etPass)
        val etConfirmPass: EditText = findViewById(R.id.etConfirmPass)
        val signup: Button = findViewById(R.id.signup)
        val signin: TextView = findViewById(R.id.signin)

        // Use these views as needed in your activity
    }
}
