package com.pourhadi.wir.data.dao

import org.jdbi.v3.sqlobject.statement.SqlQuery

interface BtcDao {

    @SqlQuery("select value from btc where code = :code AND redeemed != 1")
    fun getValueForCode(code: String): Int

}