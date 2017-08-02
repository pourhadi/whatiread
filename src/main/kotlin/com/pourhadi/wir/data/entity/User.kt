package com.pourhadi.wir.data.entity

import com.auth0.json.auth.UserInfo
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class User(
    val id: String,
    val auth0Id: String,
    val email: String,
    val nickname: String,
    val code: String
               )