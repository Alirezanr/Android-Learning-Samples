package com.example.composeapplication.home

import com.example.composeapplication.home.api.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException

@OptIn(ExperimentalCoroutinesApi::class)
//initialize and use Mockito
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    /**
     * By default, Coroutines work for Dispatcher.Main.
     * But to test Dispatcher.IO, we have to use the TestCoroutineDispatcher for replacing
     * the main dispatchers with the test dispatcher.
     **/
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = TestCoroutineRule()

    @Mock
    private lateinit var homeRepository: HomeRepository

    private lateinit var testObject: HomeViewModel

    @Before
    fun init() {
        //   testObject = HomeViewModel()
    }

    @Test
    fun `on home view model init validate loading state default value as true`() {
        //AAA Testing:
        //Assemble(Given) (We don't have any pre-requisite for this test)

        Mockito.`when`(homeRepository.getPostsData())
            .thenReturn(flow {})

        //Act(When)
        testObject = HomeViewModel(testDispatcher, homeRepository)

        //Assert(Then)
        Assert.assertEquals(true, testObject.viewState.value.isLoading)
    }

    @Test
    fun `on home view model init validate post data state default value as empty posts list`() {
        //Act
        testObject = HomeViewModel(testDispatcher, homeRepository)
        //Assert
        Assert.assertEquals(listOf<PostDto>(), testObject.viewState.value.postsListData)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `on home view model init get posts returns success with posts list data`() {
        runTest {
            //Assemble
            val post1 = testPostDtoData()
            val post2 = testPostDtoData(id = 1, title = "Post 1", body = "Post 1 Body")

            val subject = Channel<List<PostDto>>()

            Mockito.`when`(homeRepository.getPostsData()).thenReturn(subject.consumeAsFlow())

            testObject = HomeViewModel(testDispatcher, homeRepository)

            // Pre assert few things which needs to be asserted before we get api respose.
            Assert.assertEquals(emptyList<PostDto>(), testObject.viewState.value.postsListData)
            Assert.assertEquals(true, testObject.viewState.value.isLoading)

            //Act
            subject.send(listOf(post1, post2))

            //Assert
            Assert.assertEquals(listOf(post1, post2), testObject.viewState.value.postsListData)
            Assert.assertEquals(false, testObject.viewState.value.isLoading)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `on home view model init get posts returns success with different posts list data`() {
        runTest {
            //Assemble
            val post1 = testPostDtoData(id = 11, title = "Post 11", body = "Post 11 Body")
            val post2 = testPostDtoData(id = 12, title = "Post 12", body = "Post 12 Body")

            Mockito.`when`(homeRepository.getPostsData()).thenReturn(flow {
                emit(listOf(post1, post2))
            })

            //Act
            testObject = HomeViewModel(testDispatcher, homeRepository)

            //Assert
            Assert.assertEquals(listOf(post1, post2), testObject.viewState.value.postsListData)
            Assert.assertEquals(false, testObject.viewState.value.isLoading)
        }
    }


    @Test
    fun `on home view model init get posts returns error with socket connection exception`() {
        runTest {
            //Assemble
            Mockito
                .`when`(homeRepository.getPostsData())
                .thenAnswer { throw ConnectException("Network Error") }

            //Act
            testObject = HomeViewModel(testDispatcher, homeRepository)

            //Assert
            Assert.assertEquals("Network Error", testObject.viewState.value.error)
        }
    }

    @Test
    fun `on home view model init get posts returns error with 404 http exception`() =
        runTest {
            //Assemble
            Mockito.`when`(homeRepository.getPostsData())
                .thenThrow(
                    HttpException(
                        Response.error<Any>(
                            500,
                            "Error".toResponseBody("text/plain".toMediaTypeOrNull())
                        )
                    )
                )

            //Act
            testObject = HomeViewModel(testDispatcher, homeRepository)

            //Assert
            Assert.assertEquals("HTTP 500 Response.error()", testObject.viewState.value.error)
        }

    @Test
    fun `on receiving error retry get posts returns success with posts list data`() {
        //Assemble
        val post1 = testPostDtoData(id = 11, title = "Post 11", body = "Post 11 Body")
        val post2 = testPostDtoData(id = 12, title = "Post 12", body = "Post 12 Body")

        Mockito.`when`(homeRepository.getPostsData())
            .thenAnswer {
                throw ConnectException("Network Error")
            }
            .thenReturn(
                flow {
                    emit(listOf(post1, post2))
                }
            )


        //First Act
        testObject = HomeViewModel(testDispatcher, homeRepository)

        //Error Assertion
        Assert.assertEquals("Network Error", testObject.viewState.value.error)

        //Second Act
        testObject.fetchPostsData()

        //Assert Data
        Assert.assertEquals(listOf(post1, post2), testObject.viewState.value.postsListData)

    }
}