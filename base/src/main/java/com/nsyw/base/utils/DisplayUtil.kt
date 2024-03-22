package com.nsyw.base.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.roundToInt


object DisplayUtil {
    fun dpToPx(context: Context,length:Float):Int{
        val density = context.resources.displayMetrics.density
        return (length * density).roundToInt()
    }

    fun getScreenWidth(context:Context):Int{
        // 获取屏幕高度
        // 获取屏幕高度
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context:Context):Int{
        // 获取屏幕高度
        // 获取屏幕高度
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}