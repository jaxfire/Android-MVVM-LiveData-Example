package com.jaxfire.mvvmexample.ui.quotes

import android.arch.lifecycle.ViewModel
import com.jaxfire.mvvmexample.data.Quote
import com.jaxfire.mvvmexample.data.QuoteRepository


class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}