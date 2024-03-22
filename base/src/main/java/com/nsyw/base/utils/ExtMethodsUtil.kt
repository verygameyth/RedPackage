package com.nsyw.base.utils

fun Any?.toJson(): String {
    return try {
        if (this == null) return ""
        GsonUtil.gson.toJson(this)
    } catch (e: Exception) {
        ""
    }
}