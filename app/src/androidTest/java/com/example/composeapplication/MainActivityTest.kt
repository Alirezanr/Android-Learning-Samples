package com.example.composeapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        //start mock server
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            )
        )
    }

    @After
    fun tearDown() {
        //stop mock server
        mockWebServer.shutdown()
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

        activityRule.launchActivity(null)

        onView(withId(R.id.progress_bar))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.character_recyclerview))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.textview))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun testFailedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }

        activityRule.launchActivity(null)
        onView(withId(R.id.progress_bar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.character_recyclerview))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.textview))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textview))
            .check(matches(withText(R.string.something_went_wrong)))
    }
}