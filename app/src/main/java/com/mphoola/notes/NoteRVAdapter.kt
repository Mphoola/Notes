package com.mphoola.notes

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    private val noteClickDeleteInterface: NoteClickDeleteInterface,
    private val noteClickInterface: NoteClickInterface
): RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //initializing all the variables which will be added in layout file
        val noteTV: TextView = itemView.findViewById(R.id.idTVNote)
        val detailsTV: TextView = itemView.findViewById(R.id.idTVDetails)
        val deleteIV: ImageView = itemView.findViewById(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.text = allNotes[position].noteTitle
        holder.detailsTV.text =  allNotes[position].noteDescription

        holder.deleteIV.setOnClickListener {
            val dialog  = androidx.appcompat.app.AlertDialog.Builder(context)
            dialog.setTitle("Are you sure")
            dialog.setMessage("You want to delete this?")
            dialog.setCancelable(false)

            dialog.setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
                noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
            }
            dialog.setNegativeButton("No!"){ _: DialogInterface, _: Int ->

            }
            dialog.show()
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    //update list of notes
    fun updateList(newList: List<Note>){
        allNotes.clear()

        allNotes.addAll(newList)

        notifyDataSetChanged() //notify adpter of the change
    }

    interface NoteClickDeleteInterface {
        fun onDeleteIconClick(note: Note)
    }

    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }
}