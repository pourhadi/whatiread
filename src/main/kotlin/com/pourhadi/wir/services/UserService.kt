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

    fun createCode(userId: String,
                   code: String): Single<String> {
        return Single.fromCallable {
            val newId = UUID.randomUUID().toString()
            userDao.insertCode(newId, userId, code)
            newId
        }
    }

    fun getCode(id: String): Single<UserCode> {
        return Single.fromCallable {
            userDao.getCode(id)
        }
    }

    fun getFromCode(code: String): Single<UserCode> {
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
