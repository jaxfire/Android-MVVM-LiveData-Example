package com.jaxfire.mvvmexample.ui.quotes

import android.arch.lifecycle.ViewModel
import com.jaxfire.mvvmexample.data.Quote
import com.jaxfire.mvvmexample.data.QuoteRepository


class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    var filteredByActive = false

    fun getQuotes() = quoteRepository.getQuotes()

    fun getFilteredQuotes(): List<Quote>? {
        return if (filteredByActive) {
            getQuotes().value?.filter { it -> it.author.contains("A") }
        } else {
            getQuotes().value
        }
    }

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

    fun switchFilter() {
        filteredByActive = !filteredByActive
    }
}