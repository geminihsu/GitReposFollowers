package com.gemini.githubfollowers.viewmodel

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
import org.mockito.MockitoAnnotations


class GithubViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: GithubViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testApiFetchFollowersSuccess() {
        GlobalScope.launch(start = CoroutineStart.DEFAULT) {
            val followers = viewModel.fetchFollowers(TEST_USER_NAME)
            assertEquals(followers, TEST_EXPECTED_FOLLOWERS)
        }
    }

    @Test
    fun testApiFetchUserInfoSuccess() {
        GlobalScope.launch(start = CoroutineStart.DEFAULT) {
            val user = viewModel.fetchUserInfo(TEST_USER_NAME)
             assertEquals(user, TEST_EXPECTED_USER_INFO)
        }
    }
}