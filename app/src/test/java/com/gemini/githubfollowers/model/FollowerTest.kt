package com.gemini.githubfollowers.model

import com.gemini.githubfollowers.constants.TEST_FOLLOWER_AVATAR_URL
import com.gemini.githubfollowers.constants.TEST_FOLLOWER_URL
import com.gemini.githubfollowers.constants.TEST_FOLLOWER_LOGIN
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FollowerTest {
    @Mock
    lateinit var follower: Follower

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(follower.login).thenReturn(TEST_FOLLOWER_LOGIN)
        Mockito.`when`(follower.avatar_url).thenReturn(TEST_FOLLOWER_AVATAR_URL)
        Mockito.`when`(follower.followers_url).thenReturn(TEST_FOLLOWER_URL)
    }

    @Test
    fun testFollowerLogin() {
        Mockito.`when`(follower.login).thenReturn(TEST_FOLLOWER_LOGIN)
    }

    @Test
    fun testFollowerAvatarURL() {
        Mockito.`when`(follower.avatar_url).thenReturn(TEST_FOLLOWER_AVATAR_URL)
    }

    @Test
    fun testFollowerFollowersURL() {
        Mockito.`when`(follower.followers_url).thenReturn(TEST_FOLLOWER_URL)
    }
}