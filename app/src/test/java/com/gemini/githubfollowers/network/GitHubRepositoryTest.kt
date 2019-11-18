package com.gemini.githubfollowers.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gemini.githubfollowers.constants.TEST_EXPECTED_FOLLOWERS
import com.gemini.githubfollowers.constants.TEST_EXPECTED_USER_INFO
import com.gemini.githubfollowers.constants.TEST_USER_NAME
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GitHubRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: GitHubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun testFetchFollowers() {
        GlobalScope.launch(start = CoroutineStart.DEFAULT) {
            val followers = repository.getFollowers(TEST_USER_NAME).value
            assertEquals(followers?.size, 5)
            assertEquals(followers, TEST_EXPECTED_FOLLOWERS)
        }
    }

    @Test
    fun testFetchUserInfo() {
        GlobalScope.launch(start = CoroutineStart.DEFAULT) {
            val user = repository.getUserInfo(TEST_USER_NAME).value
            assertEquals(user, TEST_EXPECTED_USER_INFO)
            assertEquals(user?.login, TEST_EXPECTED_USER_INFO.login)
            assertEquals(user?.avatar_url, TEST_EXPECTED_USER_INFO.avatar_url)
            assertEquals(user?.location, TEST_EXPECTED_USER_INFO.location)
            assertEquals(user?.name, TEST_EXPECTED_USER_INFO.name)
            assertEquals(user?.email, TEST_EXPECTED_USER_INFO.email)
            assertEquals(user?.followers, TEST_EXPECTED_USER_INFO.followers)
            assertEquals(user?.following, TEST_EXPECTED_USER_INFO.following)
            assertEquals(user?.public_repos, TEST_EXPECTED_USER_INFO.public_repos)
        }
    }

}