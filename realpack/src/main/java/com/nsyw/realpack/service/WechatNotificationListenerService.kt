package com.nsyw.realpack.service

import android.app.Notification
import android.app.PendingIntent
import android.content.ComponentName
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.text.TextUtils
import android.util.Log


class WechatNotificationListenerService : NotificationListenerService() {

    private val tag = WechatNotificationListenerService::class.java.simpleName

    override fun onListenerConnected() {
        super.onListenerConnected()
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.d(tag, "WechatNotificationListenerService Disconnected..")
        requestRebind(ComponentName(this,NotificationListenerService::class.java))
    }


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // 如果该通知的包名不是微信，那么 pass 掉
        if ( PACKAGE_WX  != sbn!!.packageName) {
            return
        }
        val notification = sbn.notification ?: return
        var pendingIntent: PendingIntent? = null
        val extras = notification.extras
        if (extras != null) {
            // 获取通知内容
            val content = extras.getString(Notification.EXTRA_TEXT, "")
            if (!TextUtils.isEmpty(content) && content.contains("[微信红包]")) {
                Log.d(tag, "收到微信红包通知")
                pendingIntent = notification.contentIntent
            }
        }
        try {
            pendingIntent?.send()
            Log.d(tag, "成功：打开红包")
        } catch (e: PendingIntent.CanceledException) {
            Log.d(tag, "失败：打开失败")
            e.printStackTrace()
        }
    }

    companion object{
        private const val PACKAGE_WX="com.tencent.mm"
    }
}