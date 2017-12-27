package com.memo.supermemo

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by eka on 2017. 12. 24..
 */
@RealmClass
open class Memo(var title: String,
                var content: String,
                var createDate: Long,
                var modifiedDate: Long,
                var color: String) : RealmModel