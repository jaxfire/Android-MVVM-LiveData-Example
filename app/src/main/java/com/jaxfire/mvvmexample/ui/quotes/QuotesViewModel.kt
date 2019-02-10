package com.jaxfire.mvvmexample.ui.quotes

import android.arch.lifecycle.ViewModel
import com.jaxfire.mvvmexample.data.Quote
import com.jaxfire.mvvmexample.data.QuoteRepository


class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    var filteredByActive = false

    fun getQuotes() = quoteRepository.getQuotes()

    fun getFilteredQuotes() = getQuotes().value?.filter(if (filteredByActive) filterLetterA else noFilter)

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

    fun switchFilter() {
        filteredByActive = !filteredByActive
    }

    // Various filters could be place in a map and the keys used to populate the filter UI menu
    val noFilter: (Quote) -> Boolean = { true }
    val filterLetterA: (Quote) -> Boolean = { it.author.contains("A", ignoreCase = true) }
}