package com.ingray.ecorakshak.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.ingray.ecorakshak.R

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        val sendLink: Button = findViewById(R.id.btnSendLink)
        val email: EditText = findViewById(R.id.etEmail)
    }
}