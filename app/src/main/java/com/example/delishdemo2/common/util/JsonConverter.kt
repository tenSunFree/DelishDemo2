package com.example.delishdemo2.common.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import javax.inject.Inject

internal class JsonConverter @Inject constructor(private val moshi: Moshi) {

    @ExperimentalStdlibApi
    inline fun <reified T> toJson(clazz: T): String {
        return moshi.adapter<T>().toJson(clazz)
    }

    @ExperimentalStdlibApi
    inline fun <reified T> fromJson(json: String): T? {
        return moshi.adapter<T>().fromJson(json)
    }
}
