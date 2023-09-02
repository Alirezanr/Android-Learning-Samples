package com.example.composeapplication.home.api

import com.example.composeapplication.home.PostDto
import com.example.composeapplication.home.TestCoroutineRule
import com.example.composeapplication.home.testPostDtoData
import com.example.composeapplication.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = TestCoroutineRule()

    @Mock
    private lateinit var homeDataSource: HomeDataSource

    @Before
    fun setUp() {
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get posts data returns posts list data`() {
       runTest {
           //Assemble
           val subject = Channel<List<PostDto>>()

           val post1 = testPostDtoData()
           val post2 = testPostDtoData(id = 1, title = "Post 1", body = "Post 1 Body")

           Mockito.`when`(homeDataSource.getPostsData()).thenReturn(subject.consumeAsFlow())

           val testObject = HomeRepository(homeDataSource)

           //Act
           val testObserver = testObject.getPostsData().test(this)

           testObserver.assertNoValues()

           subject.send(listOf(post1, post2))

           //Assert
           testObserver.assertValues(listOf(post1, post2))

           testObserver.finish()
       }
    }
}