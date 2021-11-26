package com.example.projectappstickee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val note: List<Note>, val listener: (Note) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val judul = view.findViewById<TextView>(R.id.judul)
        val deskripsi = view.findViewById<TextView>(R.id.deskripsi)

        fun bindView(note: Note, listener: (Note) -> Unit){
            judul.text = note.judul
            deskripsi.text = note.isi
            itemView.setOnClickListener{
                listener(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_notes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindView(note[position], listener)
    }

    override fun getItemCount(): Int = note.size
}