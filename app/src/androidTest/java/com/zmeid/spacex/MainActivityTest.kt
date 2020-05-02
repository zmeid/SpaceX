package com.zmeid.spacex

import androidx.recyclerview.widget.RecyclerView
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.zmeid.spacex.view.ui.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Contains tests for [MainActivity].
 */
@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    /**
     * Starts the application and checks if recyclerview has some data. It ensures that we are able to do an API call and get response.
     */
    @Test
    fun recyclerviewDataTest() {
        Thread.sleep(5000)
        Assert.assertTrue(getRecyclerViewItemCount() > 0)
    }

    private fun getRecyclerViewItemCount(): Int {
        val recyclerView: RecyclerView =
            mActivityTestRule.activity.findViewById(R.id.recyclerview_launch)
        return if (recyclerView.adapter != null) {
            recyclerView.adapter!!.itemCount
        } else {
            0
        }
    }
}