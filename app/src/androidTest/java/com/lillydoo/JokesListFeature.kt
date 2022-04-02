package com.lillydoo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.lillydoo.jokes.di.idlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.core.AllOf.allOf

import org.junit.Test


class JokesListFeature : BaseUITest() {

    @Test
    fun displayScreenTitle() {
        assertDisplayed("LillyDooChallenge")
    }

    @Test
    fun displaysListOfJokes() {
        assertRecyclerViewItemCount(R.id.jokes_list, 10)

        onView(
            allOf(
                withId(R.id.joke_list_layout),
                isDescendantOfA(nthChildOf(withId(R.id.jokes_list), 0))
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingTheJokes() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysJokeSummaryDialog() {
        onView(
            allOf(
                withId(R.id.joke_list_layout),
                isDescendantOfA(nthChildOf(withId(R.id.jokes_list), 0))
            )
        ).perform(click())

        onView(withText(R.string.app_name))
            .inRoot(isDialog())
            .check(matches(isDisplayed()));
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success_response.json"))
            }
        }
    }
}