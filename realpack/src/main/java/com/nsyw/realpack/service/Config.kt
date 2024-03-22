package com.nsyw.realpack.service

object Config {
    /////////////////////////////////////////////////////////////////////////
    // 微信
    /////////////////////////////////////////////////////////////////////////
    /** 微信包名 */
    const val WechatPackageName = "com.tencent.mm"

    /** 点开红包弹窗类名 */
    const val HongBaoReceiveClassName =
        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyNotHookReceiveUI"

    /** 红包详情类名 */
    const val HongBaoDetailClassName = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI"

    const val LuckyMoneyBeforeDetailUI =
        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyBeforeDetailUI"

    /** 红包父布局资源id */
    const val HongBaoLayoutResId = "com.tencent.mm:id/bkg"

    /** 左下角“微信红包”资源id */
    const val HongBaoTextResId = "com.tencent.mm:id/a3y"

    /** 中间的“已过期|以领取”资源id */
    const val HongBaoExpiredResId = "com.tencent.mm:id/a3m"

    /** “开”图片按钮资源id */
    const val OpenButtonResId = "com.tencent.mm:id/j6g"

    ///////////////////////////
    // 聊天详情页
    //////////////////////////
    const val ChatDetailPageLayoutResId = "com.tencent.mm:id/bks"

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