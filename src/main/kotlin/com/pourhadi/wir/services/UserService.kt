package com.pourhadi.wir.services

import com.pourhadi.wir.data.dao.UserDao
import com.pourhadi.wir.data.entity.User
import com.pourhadi.wir.data.entity.UserCode
import com.pourhadi.wir.modules.DataSource
import org.jdbi.v3.core.Jdbi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Single
import java.util.*

@Service
class UserService @Autowired constructor(dataSource: DataSource) : BaseService(Jdbi.create(dataSource)) {
    val userDao: UserDao = jdbi.onDemand(UserDao::class.java)

    fun create(auth0Id: String,
               nickname: String,
               email: String): Single<User> {
        return Single.fromCallable {
            val newId = UUID.randomUUID().toString()
            val code = UUID.randomUUID().toString()
            userDao.insert(newId, auth0Id, nickname, email, code)
        }.flatMap { get(auth0Id) }
    }

    fun update(auth0Id: String,
               nickname: String,
               email: String) : Single<User> {
        return Single.fromCallable {
            userDao.update(auth0Id, nickname, email)
        }.flatMap { get(auth0Id) }
    }

    fun updateAndGetOrCreate(auth0Id: String,
                             nickname: String,
                             email: String): Single<User> {
        return get(auth0Id).flatMap { result ->
            if (result == null) {
                create(auth0Id, nickname, email)
            } else {
                update(auth0Id, nickname, email)
            }
        }

    }

    fun get(auth0Id: String): Single<User?> {
        return Single.fromCallable {
            userDao.get(auth0Id)
        }
    }

    fun getCode(id: String): Single<String> {
        return Single.fromCallable {
            userDao.getCode(id)
        }
    }

    fun getFromCode(code: String): Single<String> {
        return Single.fromCallable {
            userDao.getFromCode(code)
        }
    }

    fun getAll(): Single<List<User>> {
        return Single.fromCallable {
            userDao.getAll()
        }
    }
}
