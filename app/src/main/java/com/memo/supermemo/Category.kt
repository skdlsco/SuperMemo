package com.memo.supermemo

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by eka on 2017. 12. 24..
 */
@RealmClass
open class Category(var name: String,
                    var color: String,
                    var child: ArrayList<Any>) : RealmModel