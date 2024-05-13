package com.nsyw.realpack.service

object Config {

    /** 微信包名 */
    const val WechatPackageName = "com.tencent.mm"

    /////////////////////////////////////////////////////////////////////////
    // 微信红包
    /////////////////////////////////////////////////////////////////////////

    /** 点开红包弹窗类名 */
    const val RedPackageReceiveClassName =
        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyNotHookReceiveUI"

    /** 红包详情类名 */
    const val RedPackageDetailClassName = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI"

    const val LuckyMoneyBeforeDetailUI =
        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyBeforeDetailUI"


    /** “开”图片按钮资源id */
    const val OpenButtonResId = "com.tencent.mm:id/j6g"

    const val RedPackageZeroStrId = "com.tencent.mm:id/j6c"

    const val RedPackageDialogResId = "com.tencent.mm:id/j64"

    ///////////////////////////
    // 聊天详情页
    //////////////////////////
    /** 聊天界面父布局id */
    const val ChatDetailPageLayoutResId = "com.tencent.mm:id/bks"

    /** 红包父布局资源id */
    const val RedPackageLayoutResId = "com.tencent.mm:id/bkg"

    /** 左下角“微信红包”资源id */
    const val RedPackageTextResId = "com.tencent.mm:id/a3y"

    /** 中间的“已过期|以领取”资源id */
    const val RedPackageExpiredResId = "com.tencent.mm:id/a3m"

    /////////////////////////
    // 首页聊天列表
    //////////////////////////

    /**
     * 标题id
     */
    const val HomeRedPackageTitleResId = "android:id/text1"

    /**
     * 每个抽屉的父布局id
     */
    const val HomeRedPackageLayoutResId = "com.tencent.mm:id/cj1"

    const val HomeRedPackageNewMessageResId = "com.tencent.mm:id/o_u"

    /**
     * 包含消息内容的控件id
     */
    const val HomeRedPackageResId = "com.tencent.mm:id/ht5"

    //////////////////////////////////////////////////////////////////////
    // 企业微信
    //////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////
    // 钉钉
    //////////////////////////////////////////////////////////////////////
}