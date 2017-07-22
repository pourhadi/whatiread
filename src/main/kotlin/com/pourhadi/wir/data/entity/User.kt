package com.pourhadi.wir.data.entity

import com.auth0.json.auth.UserInfo
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class User(
    val id: String,
    val email: String,
    val nickname: String,
    val code: String
               ) {


    @JsonCreator
    constructor(userInfo: UserInfo, code: String) : this(userInfo.values["user_id"] as String,
                                                         userInfo.values["email"] as String,
                                                         userInfo.values["nickname"] as String,
                                                         code)
}