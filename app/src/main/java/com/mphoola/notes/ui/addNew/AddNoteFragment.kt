package com.mphoola.notes.ui.addNew

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mphoola.notes.Note
import com.mphoola.notes.R
import com.mphoola.notes.databinding.AddNoteFragmentBinding
import java.util.*

class AddNoteFragment : Fragment() {

    private lateinit var viewModel: AddNoteViewModel
    private lateinit var binding: AddNoteFragmentBinding

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_note_fragment, container, false)
        val context = container?.context

        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        binding.lifecycleOwner = this
        binding.addNoteViewModel = viewModel

        val editing = requireArguments().getBoolean("edit")
        if(editing){
            viewModel.inputTitle.value = arguments?.getString("title")
            viewModel.inputDescription.value = arguments?.getString("description")
        }

        binding.idBtn.setOnClickListener {
            val nt = binding.idEdtNoteName.text
            val nd = binding.idEdtNoteDesc.text
            when{
                nt.isEmpty() -> {
                    Toast.makeText(context, "Title is required", Toast.LENGTH_LONG).show()
                }
                nd.isEmpty() -> {
                    Toast.makeText(context, "Description is required", Toast.LENGTH_LONG).show()
                }
                else -> {
                    val noteTitle = viewModel.inputTitle.value!!
                    val noteDescription = viewModel.inputDescription.value!!

                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())

                    if (editing){
                        val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                       updatedNote.id = arguments?.getInt("id", -1)!!
                        viewModel.updateNote(updatedNote)
                        findNavController().navigateUp()
                        Toast.makeText(context, "$updatedNote", Toast.LENGTH_LONG).show()
                    }else{
                        viewModel.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                        Toast.makeText(context, "Note added...", Toast.LENGTH_LONG).show()
                        findNavController().navigateUp()
                    }
                }
            }

        }

        return binding.root
    }
}