package com.gemini.githubfollowers.model

import com.gemini.githubfollowers.constants.TEST_USER_INFO_AVATAR_URL
import com.gemini.githubfollowers.constants.TEST_USER_INFO_LOCATION
import com.gemini.githubfollowers.constants.TEST_USER_INFO_LOGIN
import com.gemini.githubfollowers.constants.TEST_USER_NAME
import com.gemini.githubfollowers.constants.TEST_USER_INFO_EMAIL
import com.gemini.githubfollowers.constants.TEST_USER_INFO_FOLLOWERS
import com.gemini.githubfollowers.constants.TEST_USER_INFO_FOLLOWING
import com.gemini.githubfollowers.constants.TEST_USER_INFO_PUBLIC_REPO
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserInfoTest {

    @Mock
    lateinit var userInfo: UserInfo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(userInfo.login).thenReturn(TEST_USER_INFO_LOGIN)
        Mockito.`when`(userInfo.avatar_url).thenReturn(TEST_USER_INFO_AVATAR_URL)
        Mockito.`when`(userInfo.name).thenReturn(TEST_USER_NAME)
        Mockito.`when`(userInfo.location).thenReturn(TEST_USER_INFO_LOCATION)
        Mockito.`when`(userInfo.email).thenReturn(TEST_USER_INFO_EMAIL)
        Mockito.`when`(userInfo.followers).thenReturn(TEST_USER_INFO_FOLLOWERS)
        Mockito.`when`(userInfo.following).thenReturn(TEST_USER_INFO_FOLLOWING)
        Mockito.`when`(userInfo.public_repos).thenReturn(TEST_USER_INFO_PUBLIC_REPO)
    }

    @Test
    fun testUserInfoLogin() {
        Mockito.`when`(userInfo.login).thenReturn(TEST_USER_INFO_LOGIN)
    }

    @Test
    fun testUserInfoAvatarURL() {
        Mockito.`when`(userInfo.avatar_url).thenReturn(TEST_USER_INFO_AVATAR_URL)
    }

    @Test
    fun testUserInfoName() {
        Mockito.`when`(userInfo.name).thenReturn(TEST_USER_NAME)
    }

    @Test
    fun testUserInfoLocation() {
        Mockito.`when`(userInfo.location).thenReturn(TEST_USER_INFO_LOCATION)
    }

    @Test
    fun testUserInfoEmail() {
        Mockito.`when`(userInfo.email).thenReturn(TEST_USER_INFO_EMAIL)
    }

    @Test
    fun testUserInfoPublicRepos() {
        Mockito.`when`(userInfo.public_repos).thenReturn(TEST_USER_INFO_PUBLIC_REPO)
    }

    @Test
    fun testUserInfoFollowers() {
        Mockito.`when`(userInfo.followers).thenReturn(TEST_USER_INFO_FOLLOWERS)
    }

    @Test
    fun testUserInfoFollowing() {
        Mockito.`when`(userInfo.following).thenReturn(TEST_USER_INFO_FOLLOWING)
    }
}