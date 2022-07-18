package com.quiz.jobsearch

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.quiz.jobsearch.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Test
    fun test_search_button() {
        val scenario = ActivityScenario.launch(HomeActivity::class.java)
        scenario.onActivity {
            Assert.assertEquals(it.search_btn.isEnabled, false)

        }
        onView(withId(R.id.input_edit)).perform(typeText("developer"))
        scenario.onActivity {
            Assert.assertEquals(it.search_btn.isEnabled, true)
        }
        onView(withId(R.id.search_btn)).perform(click())
    }
}