package com.quiz.jobsearch.core.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.quiz.jobsearch.R
import com.quiz.jobsearch.core.view.IView

abstract class BaseActivity : AppCompatActivity(), IView {

    abstract val fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initFragment(fragment)
    }

    private fun initFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }
}
