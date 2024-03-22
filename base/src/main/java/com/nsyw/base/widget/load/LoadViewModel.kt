package com.nsyw.base.widget.load

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nsyw.base.base.BaseViewModel

class LoadViewModel() : BaseViewModel() {

    val loadText = MutableLiveData("加载中")
}