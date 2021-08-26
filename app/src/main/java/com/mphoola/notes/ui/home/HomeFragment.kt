package com.mphoola.notes.ui.home

import android.content.Context
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
import com.mphoola.notes.databinding.HomeFragmentBinding


class HomeFragment : Fragment(), NoteRVAdapter.NoteClickInterface, NoteRVAdapter.NoteClickFavouriteInterface {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding
    lateinit var noteRV: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        noteRV = binding.notesRv
        val context = activity?.applicationContext

        val noteRVAdapter = NoteRVAdapter(this, this)

        noteRV.layoutManager = LinearLayoutManager(context)
        noteRV.adapter = noteRVAdapter

        viewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            val bundle = bundleOf(
                "edit" to false
            )
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment, bundle)
        }

        return binding.root
    }

    override fun onNoteClick(note: Note) {
        val bundle = bundleOf(
            "title" to note.noteTitle,
            "description" to note.noteDescription,
            "id" to note.id,
            "edit" to true
        )
        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment, bundle)
    }

    override fun onFavouriteIconClick(note: Note) {
        Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
    }
}