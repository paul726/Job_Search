package com.quiz.jobsearch.searchresult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quiz.jobsearch.R
import com.quiz.jobsearch.core.net.JobInfo
import kotlinx.android.synthetic.main.item_layout_job.view.*

class JobsRecyclerAdapter(
    private val jobs: List<JobInfo>,
    private val listener: OnJobItemClickListener?
) : RecyclerView.Adapter<JobsRecyclerAdapter.JobViewHolder>() {

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(jobInfo: JobInfo) {
            itemView.job_title_tv.text = jobInfo.title
            Glide.with(itemView.context).load(jobInfo.company?.logoUrl).centerCrop()
                .into(itemView.company_logo_iv)
            itemView.company_name_tv.text = jobInfo.company?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val itemVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_job, parent, false)
        return JobViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bindData(jobs[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(jobs[position])
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    interface OnJobItemClickListener {
        fun onItemClick(job: JobInfo)
    }
}