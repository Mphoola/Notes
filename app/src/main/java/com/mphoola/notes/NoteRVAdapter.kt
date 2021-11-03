package com.mphoola.notes

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
//    val context: Context,
    private val noteClickFavouriteInterface: NoteClickFavouriteInterface,
    private val noteClickInterface: NoteClickInterface
): RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //initializing all the variables which will be added in layout file
        val noteTV: TextView = itemView.findViewById(R.id.idTVNote)
        val detailsTV: TextView = itemView.findViewById(R.id.idTVDetails)
        val favouriteIV: ImageView = itemView.findViewById(R.id.idIVFavourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (allNotes[position].favourite == "1") {
            holder.favouriteIV.setBackgroundResource(R.drawable.ic_starred)
        }
        holder.noteTV.text = allNotes[position].noteTitle
        holder.detailsTV.text =  allNotes[position].noteDescription

        holder.favouriteIV.setOnClickListener { noteClickFavouriteInterface.onFavouriteIconClick(allNotes[position]) }

        holder.itemView.setOnClickListener { noteClickInterface.onNoteClick(allNotes[position]) }
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

    interface NoteClickFavouriteInterface {
        fun onFavouriteIconClick(note: Note)
    }

    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }
}