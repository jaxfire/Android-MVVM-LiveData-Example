package com.jaxfire.mvvmexample.data

// Repository is the single source of truth for your UI.
class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao) {

    fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    // Could get from DB, could get from WebService. The repo decides.
    fun getQuotes() = quoteDao.getQuotes()

    // Singleton for Repository (The Java Singleton way)
    companion object {
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: FakeQuoteDao) = instance ?: synchronized(this) {
            instance ?: QuoteRepository(quoteDao).also { instance = it }
        }
    }
}