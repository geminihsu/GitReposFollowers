package com.gemini.githubfollowers.constants

import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.model.UserInfo

const val BASE_URL = "https://api.github.com/"
const val SHOW_INITIAL = 1
const val SHOW_IMAGE = 2
const val BUNDLE_USERNAME = "username"
const val TEST_USER_NAME = "geminihsu"

/*
   Test constant variable
 */
val TEST_EXPECTED_FOLLOWERS = arrayListOf(
    Follower("himanshukandwal", "https://avatars1.githubusercontent.com/u/2539436?v=4", "https://api.github.com/users/himanshukandwal/followers"),
    Follower("dalinhuang99", "https://avatars2.githubusercontent.com/u/6508763?v=4", "https://api.github.com/users/dalinhuang99/followers"),
    Follower("jasonfesta", "https://avatars3.githubusercontent.com/u/1239243?v=4", "https://api.github.com/users/jasonfesta/followers"),
    Follower("terry77228", "https://avatars1.githubusercontent.com/u/6784416?v=4", "https://api.github.com/users/terry77228/followers"),
    Follower("shalendrasingh", "https://avatars3.githubusercontent.com/u/54352951?v=4", "https://api.github.com/users/shalendrasingh/followers")
    )

val TEST_EXPECTED_USER_INFO = UserInfo("geminihsu", "https://avatars2.githubusercontent.com/u/1829733?v=4", "Ling Rong Syu", "San Mateo", null, 5, 32, 26)

//FollowerTest const variable
const val TEST_FOLLOWER_LOGIN = "himanshukandwal"
const val TEST_FOLLOWER_AVATAR_URL = "https://avatars1.githubusercontent.com/u/2539436?v=4"
const val TEST_FOLLOWER_URL = "https://api.github.com/users/himanshukandwal/followers"

//UserInfo const variable
const val TEST_USER_INFO_LOGIN = "geminihsu"
const val TEST_USER_INFO_AVATAR_URL = "https://avatars2.githubusercontent.com/u/1829733?v=4"
const val TEST_USER_INFO_FOLLOWERS_URL = "https://api.github.com/users/geminihsu/followers"
const val TEST_USER_INFO_NAME = "Ling Rong Syu"
const val TEST_USER_INFO_LOCATION = "San Mateo"
const val TEST_USER_INFO_EMAIL = ""
const val TEST_USER_INFO_PUBLIC_REPO = 26
const val TEST_USER_INFO_FOLLOWERS = 5
const val TEST_USER_INFO_FOLLOWING = 32
