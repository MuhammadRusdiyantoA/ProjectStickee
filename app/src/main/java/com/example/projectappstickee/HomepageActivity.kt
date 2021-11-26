package com.example.projectappstickee

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomepageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        supportActionBar?.hide()

        auth = Firebase.auth

        val noteList = listOf<Note>(
            Note(
                "1",
                "Note Number 1",
                "This is note number 1"
            ),
            Note(
                "2",
                "Lorem",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            ),
            Note(
                "3",
                "Longer Lorem",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            ),
            Note(
                "4",
                "Short lorem",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            ),
            Note(
                "5",
                "Shorter lorem",
                "Lorem ipsum dolor sit amet"
            ),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter(this, noteList){
            val intent = Intent(this, NoteDetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }

        val outButton = findViewById<ImageView>(R.id.user_profile)
        outButton.setOnClickListener{
            val intent = Intent(this, OutActivity::class.java)
            startActivity(intent)
        }

        val addButton = findViewById<Button>(R.id.toAdd)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val userId = auth.uid
        if(userId != null){
            updateUI(userId)
        }
    }

    private fun updateUI(username: String) {
        val nameDisplay = findViewById<TextView>(R.id.username_display)
        nameDisplay.text = "$username"
    }
}