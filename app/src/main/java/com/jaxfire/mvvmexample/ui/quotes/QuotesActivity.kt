package com.jaxfire.mvvmexample.ui.quotes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jaxfire.mvvmexample.R
import com.jaxfire.mvvmexample.data.Quote
import com.jaxfire.mvvmexample.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quotes.*

class QuotesActivity : AppCompatActivity() {

    private lateinit var viewModel: QuotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val factory = InjectorUtils.provideQuotesViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        initialiseUi()
    }

    private fun initialiseUi() {

        viewModel.getQuotes().observe(this, Observer {
            updateAdapter()
        })

        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }

        button_filter.setOnClickListener {
            viewModel.switchFilter()
            updateAdapter()
        }
    }

    private fun updateAdapter() {

        // RECYCLERVIEW LOGIC
        // adapter.setWords(words)

        val stringBuilder = StringBuilder()
        viewModel.getFilteredQuotes()?.forEach { quote ->
            stringBuilder.append("$quote\n\n")
        }
        textView_quotes.text = stringBuilder.toString()
    }
}
