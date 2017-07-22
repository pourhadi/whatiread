package com.pourhadi.wir.data.dao

import com.pourhadi.wir.data.entity.User
import com.pourhadi.wir.data.entity.UserCode
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserDao {

    @SqlQuery("select * from user_codes where userId=:id")
    fun getCode(@Bind("id") id:String): UserCode

    @SqlQuery("select * from user_codes where code=:code")
    fun getFromCode(@Bind("code") code:String): UserCode

    @SqlUpdate("insert into user_codes SET userId=:code.userId, code=:code.code")
    fun insertCode(code: UserCode)

    @SqlQuery("select * from users")
    fun getAll(): List<User>
}