package com.nsyw.base.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun showToast(context: Context, msg: String?) {
        if (msg.isNullOrBlank()) return
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(context: Context, msg: String?) {
        if (msg.isNullOrBlank()) return
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}