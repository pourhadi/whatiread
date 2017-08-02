package com.pourhadi.wir.data.dao

import com.pourhadi.wir.data.entity.User
import com.pourhadi.wir.data.entity.UserCode
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import javax.management.monitor.StringMonitor

interface UserDao {

    @SqlQuery("select code from users where id=:id")
    fun getCode(@Bind("id") id: String): String

    @SqlQuery("select id from users where code=:code")
    fun getFromCode(@Bind("code") code: String): String

    @SqlUpdate("insert into users SET id=:id, nickname=:nickname, email=:email, auth0Id=:auth0Id, code=:code")
    fun insert(id: String,
                   auth0Id: String,
                   nickname: String,
                   email: String,
                   code: String)

    @SqlUpdate("update users SET nickname=:nickname, email=:email where auth0Id=:auth0Id")
    fun update(auth0Id:String,
               nickname: String,
               email:String)

    @SqlQuery("select * from users where auth0Id=:auth0Id")
    fun get(auth0Id: String): User?

    @SqlQuery("select * from users")
    fun getAll(): List<User>
}