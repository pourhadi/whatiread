package com.pourhadi.wir.data.dao

import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface BtcDao {

    @SqlQuery("select value from btc where code = :code AND redeemed = 0")
    fun getValueForCode(code: String): Int

    @SqlUpdate("update btc SET redeemed = :redeemed WHERE code = :code")
    fun setRedeemed(redeemed: Boolean,
                    code: String)
}