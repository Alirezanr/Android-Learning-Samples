package com.example.composeapplication.home.api

import com.example.composeapplication.home.PostDto
import com.example.composeapplication.home.TestCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeDataSourceTest {
    @Mock
    private lateinit var homeDataService: HomeDataService
    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var homeDataSource: HomeDataSource

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = TestCoroutineRule()

    @Before
    fun setup() {
        homeDataSource = HomeDataSource(dispatcher, homeDataService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPostsData should emit correct list of posts`() = runTest {
        // Arrange
        val expectedPosts = listOf(PostDto(1, "Title 1", "Body 1"), PostDto(2, "Title 2", "Body 2"))
        `when`(homeDataService.getPostsData()).thenReturn(expectedPosts)
        // Act
        val actualPosts = homeDataSource.getPostsData().toList().flatten()
        // Assert
        assertEquals(expectedPosts, actualPosts)
    }
}