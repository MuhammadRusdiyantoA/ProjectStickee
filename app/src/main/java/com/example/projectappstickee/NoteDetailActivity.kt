package com.example.projectappstickee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        supportActionBar?.hide()

        val parcel = intent.getParcelableExtra<Note>(HomepageActivity.INTENT_PARCELABLE)

        val judul = findViewById<EditText>(R.id.judul_update)
        val isi = findViewById<EditText>(R.id.konten_update)

        judul.setText(parcel?.judul)
        isi.setText(parcel?.isi)

        val backButton = findViewById<Button>(R.id.backButton_update)
        backButton.setOnClickListener{
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }

        val updateButton = findViewById<Button>(R.id.update)
        updateButton.setOnClickListener{
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }

        val deleteButton = findViewById<Button>(R.id.delete)
        deleteButton.setOnClickListener{
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }
    }
}