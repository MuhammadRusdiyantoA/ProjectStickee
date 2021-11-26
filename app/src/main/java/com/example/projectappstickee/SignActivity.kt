package com.example.projectappstickee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        supportActionBar?.hide()

        auth = Firebase.auth

        val signButton = findViewById<Button>(R.id.sign)
        signButton.setOnClickListener{
            signUp()
        }

        val loginButton = findViewById<TextView>(R.id.toLogin)
        loginButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp() {
        val email = findViewById<EditText>(R.id.email_sign)
        val pass = findViewById<EditText>(R.id.pass_sign)

        //ngecek kosong apa gak
        if(email.text.toString().isEmpty()){
            email.error = "Please enter an email..."
            email.requestFocus()
            return
        }

        //ngecek email ada '@'-nya apa gak
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
        {
            email.error = "Please enter a valid email address..."
            email.requestFocus()
            return
        }

        //ngecek pasword kosong apa gak
        if(pass.text.toString().isEmpty()){
            pass.error = "Please enter a password..."
            pass.requestFocus()
            return
        }

        if (email.text.toString().isNotEmpty() and pass.text.toString().isNotEmpty()){
            //daftarin user
            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val toast = Toast.makeText(this, "Akun berhasil terdaftar!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomepageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Akun gagal dibuat, coba lagi setelah beberapa saat...",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}