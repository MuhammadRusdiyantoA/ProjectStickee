package com.example.projectappstickee

import android.content.Intent
import android.media.MediaCodec
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        auth = Firebase.auth

        val loginButton = findViewById<Button>(R.id.login)
        loginButton.setOnClickListener{
            logIn()
        }

        val signButton = findViewById<TextView>(R.id.toSign)
        signButton.setOnClickListener{
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logIn() {
        val email = findViewById<EditText>(R.id.email_login)
        val pass = findViewById<EditText>(R.id.pass_login)

        if(email.text.toString().isEmpty()){
            email.error = "Please enter an email address..."
            email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Please enter a valid email address..."
            email.requestFocus()
            return
        }

        if (pass.text.toString().isEmpty()) {
            pass.error = "Please enter a password..."
            pass.requestFocus()
            return
        }

        if (email.text.toString().isNotEmpty() and pass.text.toString().isNotEmpty()) {
            auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val username = auth.uid
                        Toast.makeText(baseContext, "Selamat datang kembali, $username!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomepageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Autentikasi gagal, coba beberapa saat lagi...",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


    public override fun onStart() {
        super.onStart()
        // check pengguna sudah login ato belum
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}