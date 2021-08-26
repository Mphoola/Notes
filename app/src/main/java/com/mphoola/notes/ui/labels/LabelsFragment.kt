package com.mphoola.notes.ui.labels

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mphoola.notes.R

class LabelsFragment : Fragment() {

    companion object {
        fun newInstance() = LabelsFragment()
    }

    private lateinit var viewModel: LabelsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.labels_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LabelsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}