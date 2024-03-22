package com.nsyw.realpack.service

import android.accessibilityservice.AccessibilityService
import android.app.Notification
import android.graphics.PixelFormat
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
import android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
import android.view.WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.nsyw.base.base.BaseApp
import com.nsyw.base.utils.DisplayUtil
import com.nsyw.realpack.R

class AutoOpenLuckyMoneyService : AccessibilityService() {

    private val tag = AutoOpenLuckyMoneyService::class.java.simpleName

    /** 是正在开红包 */
    private var isOpening = false

    private var isLooking = false

    override fun onServiceConnected() {
        serviceInfo.packageNames = arrayOf(Config.WechatPackageName)
        initView()
        Log.d(tag, "AutoOpenLuckyMoneyService Connected..")
    }

    override fun onDestroy() {
        Log.d(tag, "AutoOpenLuckyMoneyService Destroy..")
    }

    override fun onInterrupt() {
        Log.d(tag, "AutoOpenLuckyMoneyService Interrupt..")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Log.d(tag, "${event.className} : ${event.toString()}")
        when (event.eventType) {
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> {
                if (event.parcelableData != null && event.parcelableData is Notification && event.text.toString()
                        .contains("[微信红包]")
                ) {
                    //收到微信红包通知
                    Log.d(tag, "收到微信红包通知")
                    val notifyData = event.parcelableData as Notification
                    val notifyIntent = notifyData.contentIntent
                    try {
                        notifyIntent.send()
                        Log.d(tag, "成功：打开红包通知")
                    } catch (e: Exception) {
                        Log.e(tag, "错误：${e.printStackTrace()}")
                    }
                }
            }

            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                if (!isOpening && event.className == Config.HongBaoReceiveClassName) {
                    Log.d(tag, "显示红包弹窗")
                    isOpening = if (openHongbao(rootInActiveWindow ?: return)) {
                        //点击开按钮
                        Log.d(tag, "成功：打开红包")
                        true
                    } else {
                        //不能开 返回聊天界面
                        Log.e(tag, "失败：打开失败")
                        performGlobalAction(GLOBAL_ACTION_BACK)
                        false
                    }
                } else if (isOpening && (event.className == Config.HongBaoDetailClassName || event.className == Config.LuckyMoneyBeforeDetailUI)) {
                    Log.d(tag, "进入红包详情页")
                    performGlobalAction(GLOBAL_ACTION_BACK)
                    isOpening = false
                }

            }

            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                if (!isLooking) {
                    if (isChatListPage(rootInActiveWindow)) {
                        // 聊天列表
                        findRedPackageInChatList(rootInActiveWindow)

                    } else if (isChatDetailPage(rootInActiveWindow)) {
                        // 聊天详情
                        if (!findAndClickHongbao(rootInActiveWindow)) {
                            performGlobalAction(GLOBAL_ACTION_BACK)
                        }
                    }
                }
            }
        }
    }

    private fun openHongbao(nodeInfo: AccessibilityNodeInfo): Boolean {
        val nodes = nodeInfo.findAccessibilityNodeInfosByViewId(Config.OpenButtonResId)
        if (nodes.size == 0) return false

        val openBtn = nodes[0]

        //过滤不能点击
        if (!openBtn.isClickable) return false
        openBtn.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        return true
    }

    private fun findAndClickHongbao(nodeInfo: AccessibilityNodeInfo): Boolean {

        isLooking = true
        val list = nodeInfo.findAccessibilityNodeInfosByViewId(Config.HongBaoLayoutResId)
        if (list.isNullOrEmpty()) {
            Log.e(tag, "聊天页面未找到红包")
            isLooking = false
            return false
        } else {
            Log.d(tag, "聊天页面找到红包")
        }

        val rootRect = Rect()
        nodeInfo.getBoundsInScreen(rootRect)

        for (i in list.size - 1 downTo 0) {
            val node = list[i]

            //根据左下角“微信红包”资源id过滤红包消息
            if (node.findAccessibilityNodeInfosByViewId(Config.HongBaoTextResId).size == 0) continue

            //过滤已领取|已过期
            if (node.findAccessibilityNodeInfosByViewId(Config.HongBaoExpiredResId).size > 0) continue

            if (!node.isClickable) continue
            Log.d(tag, "点击红包")
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            isLooking = false
            return true
        }
        isLooking = false
        return false
    }

    private fun findRedPackageInChatList(nodeInfo: AccessibilityNodeInfo): Boolean {
        isLooking = true
        Log.d(tag, "聊天列表开始找红包...")
        val list = nodeInfo.findAccessibilityNodeInfosByViewId(Config.HomeRedPackageLayoutResId)
        if (list.isNullOrEmpty()) {
            Log.e(tag, "聊天列表暂无消息")
            isLooking = false
            return false
        }

        val rootRect = Rect()
        nodeInfo.getBoundsInScreen(rootRect)

        for (i in list.size - 1 downTo 0) {
            val node = list[i]
            val contentView = node.findAccessibilityNodeInfosByViewId(Config.HomeRedPackageResId)
            if (contentView.size == 0) continue
            contentView.forEach {
                if (it.text.contains("[微信红包]")) {
                    if (Runtime.NeedFilterSelf) {
                        //红包矩形位置离右边更近
                        if (it.text.indexOf("[微信红包]") == 0) return@forEach
                    }

                    if (!node.isClickable) return@forEach
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    isLooking = false
                    return true
                }
            }
        }
        isLooking = false
        return false
    }

    private fun initView() {
        try {
            val wm = this.getSystemService(WINDOW_SERVICE) as? WindowManager
            val lp = WindowManager.LayoutParams().apply {
                type = TYPE_ACCESSIBILITY_OVERLAY // 因为此权限才能展现处理
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
                format = PixelFormat.TRANSLUCENT
                flags = flags or
                        FLAG_LAYOUT_NO_LIMITS or
                        FLAG_NOT_TOUCHABLE or  // 透传接触事情
                        FLAG_NOT_FOCUSABLE or  // 透传输入事情
                        FLAG_LAYOUT_IN_SCREEN
                width = DisplayUtil.dpToPx(BaseApp.getApp(), 60f)
                height = DisplayUtil.dpToPx(BaseApp.getApp(), 60f)
                gravity = Gravity.END or Gravity.CENTER_VERTICAL
            }
            val view = LayoutInflater.from(this).inflate(R.layout.red_float, null)
            wm?.addView(view, lp)
        } catch (e: Exception) {
            Log.e(tag, e.toString())
        }

    }

    private fun isChatListPage(rootNode: AccessibilityNodeInfo): Boolean {
        val nodeList = rootNode.findAccessibilityNodeInfosByViewId(Config.HomeRedPackageTitleResId)
        return nodeList.any { it.text.contains("微信") }
    }

    private fun isChatDetailPage(rootNode: AccessibilityNodeInfo): Boolean {
        val nodeList = rootNode.findAccessibilityNodeInfosByViewId(Config.ChatDetailPageLayoutResId)
        return !nodeList.isNullOrEmpty()
    }
}