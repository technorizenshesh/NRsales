package com.nr.nrsales.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nr.nrsales.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetAQuoteViewModel
@Inject constructor(
    private val repository: Repository,
    application: Application) :
    AndroidViewModel(application) {



}