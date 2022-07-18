package com.quiz.jobsearch

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.quiz.jobsearch.Extras.Companion.KEY_JOBS_LIST
import com.quiz.jobsearch.core.net.Company
import com.quiz.jobsearch.core.net.JobInfo
import com.quiz.jobsearch.core.net.JobsData
import com.quiz.jobsearch.searchresult.SearchResultsActivity
import kotlinx.android.synthetic.main.fragment_search_results.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchResultActivityTest {

    private fun mockJobData(): JobsData {
        val company1 = Company("google", "")
        val job1 = JobInfo("Android Developer", company1, "great position")

        val company2 = Company("facebook", "")
        val job2 = JobInfo("Android engineer", company2, "great position")

        val company3 = Company("netflix", "")
        val job3 = JobInfo("Android engineer", company3, "great position")

        return JobsData(listOf(job1, job2, job3))
    }

    @Test
    fun test_search_result_ui() {
        val intent = Intent(
            InstrumentationRegistry.getInstrumentation().context,
            SearchResultsActivity::class.java
        )
        intent.putExtra(KEY_JOBS_LIST, mockJobData())
        val scenario: ActivityScenario<SearchResultsActivity> = ActivityScenario.launch(intent)
        scenario.onActivity {
            Assert.assertTrue(it.counter_tv.text.contains("3"))
            Assert.assertTrue(it.job_list_recycler_v.childCount == 3)
        }

    }

}