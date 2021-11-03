package com.mphoola.notes.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mphoola.notes.Note
import com.mphoola.notes.NoteRVAdapter
import com.mphoola.notes.R
import com.mphoola.notes.databinding.FavouritesFragmentBinding

class FavouritesFragment : Fragment(), NoteRVAdapter.NoteClickFavouriteInterface, NoteRVAdapter.NoteClickInterface {

    private lateinit var viewModel: FavouritesViewModel
    private lateinit var binding: FavouritesFragmentBinding
    private lateinit var noteRV: RecyclerView
    private lateinit var noteFavourite: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favourites_fragment, container, false)
        viewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)

        val context = activity?.applicationContext
        noteRV = binding.notesRv

        val noteRVAdapter = NoteRVAdapter(this, this)


        noteRV.layoutManager = LinearLayoutManager(context)
        noteRV.adapter = noteRVAdapter

        viewModel.allFavouriteNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

//        if(noteRVAdapter.itemCount == 0){
//            binding.textInfo.visibility = View.VISIBLE
//            Toast.makeText(context, "empty list", Toast.LENGTH_LONG).show()
//            println(noteRVAdapter.itemCount)
//        }
        return binding.root
    }

    override fun onFavouriteIconClick(note: Note) {
        noteFavourite = when {
            (note.favourite == "1") -> {
                "0"
            }
            else -> {
                "1"
            }
        }
        val result = when{
            (noteFavourite == "1") -> {"Note marked as favourite"}else->{"Note favoured updated"}
        }
        viewModel.updateNoteFavourite(note.id, noteFavourite)
        Toast.makeText(context, result, Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val bundle = bundleOf(
            "title" to note.noteTitle,
            "description" to note.noteDescription,
            "id" to note.id,
            "edit" to true
        )
        findNavController().navigate(R.id.action_favouritesFragment_to_addNoteFragment, bundle)
    }
}