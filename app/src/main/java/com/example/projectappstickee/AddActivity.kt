package com.example.projectappstickee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.hide()

        auth = Firebase.auth

        val backButton = findViewById<Button>(R.id.backButton_add)
        backButton.setOnClickListener{
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }

        val addButton = findViewById<Button>(R.id.add_note)
        addButton.setOnClickListener{
            addNote()
        }
    }

    private fun addNote() {
        val judul = findViewById<EditText>(R.id.judul_add)
        val isi = findViewById<EditText>(R.id.konten_add)

        if (judul.text.toString().isEmpty()){
            judul.error = "Please enter a title for the sticky note..."
            judul.requestFocus()
            return
        }

        if (isi.text.toString().isEmpty()){
            isi.error = "Please enter the content of the sticky note..."
            isi.requestFocus()
            return
        }

//        val ref = FirebaseDatabase.getInstance().getReference("notes")
//        val noteId = ref.push().key
//        val note = Note(noteId.toString(), judul.text.toString(), isi.text.toString())
//
//        if (noteId != null){
//            ref.child(noteId.toString()).setValue(note).addOnCompleteListener{
//                Toast.makeText(this, "Note berhasil dibuat!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, HomepageActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//        else {
//            Toast.makeText(this, "WTH", Toast.LENGTH_SHORT).show()
//        }
    }
}