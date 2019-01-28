package com.jaxfire.mvvmexample.utilities

import com.jaxfire.mvvmexample.data.FakeDatabase
import com.jaxfire.mvvmexample.data.QuoteRepository
import com.jaxfire.mvvmexample.ui.quotes.QuotesViewModelFactory


object InjectorUtils {

    fun provideQuotesViewModelFactory() : QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}