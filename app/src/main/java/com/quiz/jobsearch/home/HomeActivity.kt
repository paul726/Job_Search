package com.quiz.jobsearch.home

import com.quiz.jobsearch.core.view.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override val fragment = HomeFragment()
}