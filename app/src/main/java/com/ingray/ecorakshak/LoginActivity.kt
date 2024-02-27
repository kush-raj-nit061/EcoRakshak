package com.ingray.ecorakshak.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ingray.ecorakshak.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email: EditText = findViewById(R.id.etEmail)
        val password: EditText = findViewById(R.id.etPassword)
        val forgetPass: TextView = findViewById(R.id.tvForget)
        val login: Button = findViewById(R.id.login)
        val signUp: TextView = findViewById(R.id.tvSignup)

        // You can use these views as needed in your activity
    }
}
