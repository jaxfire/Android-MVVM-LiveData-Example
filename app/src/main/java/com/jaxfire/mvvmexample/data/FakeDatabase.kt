package com.jaxfire.mvvmexample.data

// Holder for all of the DAOs
class FakeDatabase private constructor() {

    val quoteDao = FakeQuoteDao()

    // Singleton for Room database (The Java Singleton way)
    companion object {
        @Volatile private var instance: FakeDatabase? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FakeDatabase().also { instance = it }
        }
    }
}