package com.jaxfire.mvvmexample.ui.quotes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jaxfire.mvvmexample.data.QuoteRepository


class QuotesViewModelFactory(private val quoteRepository: QuoteRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quoteRepository) as T
    }
}