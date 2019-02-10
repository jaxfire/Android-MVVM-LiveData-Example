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

    // TODO: This will be in the recyclerview's adapter
    private var isFiltered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val factory = InjectorUtils.provideQuotesViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        initialiseUi()
    }

    private fun initialiseUi() {

        viewModel.getQuotes().observe(this, Observer { quotes ->
            setData(quotes)
        })

        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }

        button_filter.setOnClickListener {
            // TODO: adapter.toggleFilter())
            isFiltered = !isFiltered
            setData(viewModel.getQuotes().value)
        }
    }


    // This will be in the recyclerview's adapter
    private fun setData(quotes: List<Quote>?) {
        // Update UI - This would be notifyDataSetChanged()
        // Apply filter. This would be the 'active' property of a rocket.
        val stringBuilder = StringBuilder()
        quotes?.filter(if (isFiltered) filterLetterA else noFilter)?.forEach { quote ->
            stringBuilder.append("$quote\n\n")
        }
        textView_quotes.text = stringBuilder.toString()
    }

    // Various filters could be place in a map and the keys used to populate the filter UI menu
    val noFilter: (Quote) -> Boolean = { true }
    val filterLetterA: (Quote) -> Boolean = { it.author.contains("A", ignoreCase = true) }
}
