package com.mphoola.notes.ui.trashed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mphoola.notes.Note
import com.mphoola.notes.NoteRVAdapter
import com.mphoola.notes.R
import com.mphoola.notes.databinding.TrashedFragmentBinding

class TrashedFragment : Fragment(), NoteRVAdapter.NoteClickInterface, NoteRVAdapter.NoteClickFavouriteInterface {

    private lateinit var viewModel: TrashedViewModel
    lateinit var binding: TrashedFragmentBinding
    private lateinit var noteRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TrashedViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.trashed_fragment, container, false)

        val context = activity?.applicationContext
        noteRV = binding.notesRv

        val noteRVAdapter = NoteRVAdapter( this,this)

        noteRV.layoutManager = LinearLayoutManager(context)
        noteRV.adapter = noteRVAdapter

        viewModel.allTrashedNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        return binding.root
    }

    override fun onNoteClick(note: Note) {

    }

    override fun onFavouriteIconClick(note: Note) {

    }
}