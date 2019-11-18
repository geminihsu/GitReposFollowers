package com.gemini.githubfollowers.model

data class UserInfo(
    val login: String,
    val avatar_url:  String,
    val name:  String,
    val location:  String,
    val email:  String? = null,
    val public_repos:  Int,
    val followers:  Int,
    val following:  Int
)