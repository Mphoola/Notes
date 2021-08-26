package com.mphoola.notes.ui.trashed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mphoola.notes.R

class TrashedFragment : Fragment() {

    companion object {
        fun newInstance() = TrashedFragment()
    }

    private lateinit var viewModel: TrashedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trashed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrashedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}