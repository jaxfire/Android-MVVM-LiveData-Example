package com.jaxfire.mvvmexample.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

// DAO - The interface which defines all of the available actions on a specific database table.
class FakeQuoteDao {
    private val quoteList = mutableListOf<Quote>()
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        quotes.value = quoteList
    }

    fun getQuotes() = quotes as LiveData<List<Quote>>

    fun getFilteredQuotes() = quoteList.filter { it.author.contains("A") }
}
