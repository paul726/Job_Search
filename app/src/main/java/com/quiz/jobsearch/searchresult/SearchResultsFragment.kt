package com.quiz.jobsearch.searchresult

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.quiz.jobsearch.Extras
import com.quiz.jobsearch.Extras.Companion.KEY_JOBS_LIST
import com.quiz.jobsearch.R
import com.quiz.jobsearch.core.net.JobInfo
import com.quiz.jobsearch.core.net.JobsData
import com.quiz.jobsearch.core.view.fragment.BaseFragment
import com.quiz.jobsearch.searchdetail.JobDetailActivity
import kotlinx.android.synthetic.main.fragment_search_results.*

class SearchResultsFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_search_results

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jobsData = activity?.intent?.extras?.getSerializable(KEY_JOBS_LIST) as JobsData?

        back_view?.setOnClickListener {
            activity?.finish()
        }

        jobsData?.let {
            counter_tv.text = "Job counts: " + (it.jobs?.size ?: 0).toString()
            it.jobs?.filterNotNull()?.let { jobList ->
                job_list_recycler_v.layoutManager = LinearLayoutManager(context)
                job_list_recycler_v.adapter = JobsRecyclerAdapter(jobList,
                    object : JobsRecyclerAdapter.OnJobItemClickListener {
                        override fun onItemClick(job: JobInfo) {
                            val intent = Intent(context, JobDetailActivity::class.java)
                            intent.putExtra(Extras.KEY_JOB_DETAIL, job)
                            startActivity(intent)
                        }
                    })
            }
        }

    }
}