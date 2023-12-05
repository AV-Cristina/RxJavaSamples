package com.rxjavasamples.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.rxjavasamples.R
import io.reactivex.rxjava3.core.Observable

class SearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val searchEditText: EditText by lazy { findViewById(R.id.search_edit_text) }
    private val deleteTextButton: ImageView by lazy { findViewById(R.id.delete_text_button) }

    val onTextChangedObservable by lazy {
        Observable.create { subscriber ->
            // каждый раз, когда изменяется текст мы передаем его подписчику
            searchEditText.doAfterTextChanged { text ->
                subscriber.onNext(text.toString())
            }
        }
    }

    private var hint = ""
    private var isCanselVisibility: Boolean = true

    init {
        LayoutInflater.from(context).inflate(R.layout.view_search_toolbar, this)
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.SearchBarView).apply {
                hint = getString(R.styleable.SearchBarView_hint).orEmpty()
                isCanselVisibility = getBoolean(R.styleable.SearchBarView_cancel_visibility, false)
                recycle()
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        searchEditText.hint = hint
        if(!isCanselVisibility) deleteTextButton.visibility = View.GONE
        deleteTextButton.setOnClickListener {
            searchEditText.text.clear()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        searchEditText.doAfterTextChanged { text ->
            if (!text.isNullOrEmpty() && !deleteTextButton.isVisible) {
                deleteTextButton.visibility = View.VISIBLE
            }
            if (text.isNullOrEmpty() && deleteTextButton.isVisible) {
                deleteTextButton.visibility = View.GONE
            }
        }
    }
}
