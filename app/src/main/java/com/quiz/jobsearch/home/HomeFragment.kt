package com.quiz.jobsearch.home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.quiz.jobsearch.AppApplication
import com.quiz.jobsearch.Extras.Companion.KEY_JOBS_LIST
import com.quiz.jobsearch.R
import com.quiz.jobsearch.core.view.fragment.BaseFragment
import com.quiz.jobsearch.searchresult.SearchResultsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    companion object {
        const val SHARED_FILE = "search_history"
        const val KEY_HISTORY = "key_history"
    }

    private val mViewModel: HomeViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_edit.addTextChangedListener {
            afterTextChanged(it?.isEmpty())
        }

        search_btn.setOnClickListener {
            val content = input_edit.editableText.toString()
            if (content.isNotBlank()) {
                mViewModel.doSearch(AppApplication.INSTANCE.queryTemplate.replace("##", content))
            }
        }

        val stringSet = getLocalHistoryInput()
        flow_container.apply {
            stringSet?.forEach { text ->
                val itemText = TextView(context)
                itemText.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                itemText.text = text
                addView(itemText)
            }
        }

        observe()
    }

    private fun observe() {
        mViewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            progress_bar.isVisible = isLoading
        }

        mViewModel.searchResultLiveData.observe(viewLifecycleOwner) {
            saveHistoryInputToLocal(input_edit.editableText.toString())
            val intent = Intent(context, SearchResultsActivity::class.java)
            intent.putExtra(KEY_JOBS_LIST, it)
            startActivity(intent)
        }
    }

    private fun getLocalHistoryInput(): List<String>? {
        return context?.getSharedPreferences(SHARED_FILE, MODE_PRIVATE)
            ?.getString(KEY_HISTORY, null)?.let {
                it.split(":").filter { item ->
                    item.isNotEmpty()
                }
            }
    }

    private fun saveHistoryInputToLocal(input: String) {
        val list = getLocalHistoryInput()?.toMutableList() ?: mutableListOf()
        if (list.size >= 5) {
            list.removeAt(4)
        }
        list.add(0, input)
        val content = StringBuilder()
        for (item in list) {
            content.append(item)
            content.append(":")
        }
        content.deleteCharAt(content.length - 1)
        context?.getSharedPreferences(SHARED_FILE, MODE_PRIVATE)
            ?.edit()
            ?.putString(KEY_HISTORY, content.toString())
            ?.apply()
    }

    private fun afterTextChanged(isEmpty: Boolean?) {
        search_btn?.isEnabled = isEmpty != true
    }
}