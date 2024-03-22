package com.nsyw.realpack.vm

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.accessibility.AccessibilityManager
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.nsyw.base.base.BaseApp
import com.nsyw.base.base.BaseViewModel
import com.nsyw.base.utils.DisplayUtil

class MainViewModel : BaseViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

    private val _notificationOpenStatus = MutableLiveData(false)
    val notificationOpenStatus: LiveData<Boolean> = _notificationOpenStatus

    private val _accessibilityOpenStatus = MutableLiveData(false)
    val accessibilityOpenStatus: LiveData<Boolean> = _accessibilityOpenStatus

    val notificationDesc: LiveData<SpannableStringBuilder> = _notificationOpenStatus.map {
        SpannableStringBuilder("监听通知权限：").apply {
            setSpan(
                ForegroundColorSpan(Color.parseColor("#666666")),
                0,
                6,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            if (it) {
                append(
                    "已开启",
                    ForegroundColorSpan(Color.parseColor("#FF82CA34")),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                append(
                    "未开启",
                    ForegroundColorSpan(Color.parseColor("#FFD0021B")),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            setSpan(
                AbsoluteSizeSpan(DisplayUtil.dpToPx(BaseApp.getApp(), 24f)),
                6,
                10,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    val accessibilityDesc: LiveData<SpannableStringBuilder> = _accessibilityOpenStatus.map {
        SpannableStringBuilder("无障碍服务权限：").apply {
            setSpan(
                ForegroundColorSpan(Color.parseColor("#666666")),
                0,
                7,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            if (it) {
                append(
                    "已开启",
                    ForegroundColorSpan(Color.parseColor("#FF82CA34")),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                append(
                    "未开启",
                    ForegroundColorSpan(Color.parseColor("#FFD0021B")),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            setSpan(
                AbsoluteSizeSpan(DisplayUtil.dpToPx(BaseApp.getApp(), 24f)),
                7,
                11,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    val notificationBtnDesc: LiveData<String> = _notificationOpenStatus.map {
        if (it) {
            "关闭权限"
        } else {
            "开启权限"
        }
    }

    val accessibilityBtnDesc: LiveData<String> = _accessibilityOpenStatus.map {
        if (it) {
            "关闭服务"
        } else {
            "开启服务"
        }
    }

    fun checkStatus(context: Context,accessibilityManager:AccessibilityManager) {
        _accessibilityOpenStatus.value = accessibilityManager.isEnabled

        val packages = NotificationManagerCompat.getEnabledListenerPackages(context)
        _notificationOpenStatus.value = packages.contains(context.packageName)
    }

}