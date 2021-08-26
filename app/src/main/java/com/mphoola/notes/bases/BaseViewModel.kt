package com.mphoola.notes.bases

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel

class BaseViewModel: ViewModel(), Observable {
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}