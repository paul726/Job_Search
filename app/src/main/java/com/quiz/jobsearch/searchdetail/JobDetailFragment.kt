package com.quiz.jobsearch.searchdetail

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.quiz.jobsearch.Extras
import com.quiz.jobsearch.R
import com.quiz.jobsearch.core.net.JobInfo
import com.quiz.jobsearch.core.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_job_detail.*
import kotlinx.android.synthetic.main.fragment_search_results.back_view

class JobDetailFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_job_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jobData = activity?.intent?.getSerializableExtra(Extras.KEY_JOB_DETAIL) as JobInfo?

        back_view?.setOnClickListener {
            activity?.finish()
        }

        job_title_tv?.text = jobData?.title
        Glide.with(this).load(jobData?.company?.logoUrl).into(company_logo_iv)
        company_name_tv.text = jobData?.company?.name
        description_tv.text = jobData?.description
    }
}