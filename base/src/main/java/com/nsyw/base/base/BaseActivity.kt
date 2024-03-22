package com.nsyw.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nsyw.base.utils.ToastUtil
import com.nsyw.base.widget.load.LoadDialogUtil
import com.nsyw.base.widget.load.LoadState

open class BaseActivity : AppCompatActivity() {

    var viewModelCopy: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelCopy?.loadState?.observe(this) {
            when (it.state) {
                LoadState.LOAD_SHOW -> {
                    LoadDialogUtil.showDialog(
                        loadText = it.msg,
                        fragmentManager = supportFragmentManager
                    )
                }

                else -> {
                    LoadDialogUtil.hideDialog()
                }
            }
        }
        viewModelCopy?.toastMsg?.observe(this) {
            ToastUtil.showToast(this@BaseActivity, it)
        }
    }


    inline fun <reified T : BaseViewModel> getViewModel(): T {
        viewModelCopy = ViewModelProvider(this)[T::class.java]
        return viewModelCopy as T
    }

    fun showToast(msg: String?) {
        if (!msg.isNullOrBlank()) {
            ToastUtil.showToast(this, msg)
        }
    }

    fun showLongToast(msg: String?) {
        if (!msg.isNullOrBlank()) {
            ToastUtil.showLongToast(this, msg)
        }
    }
}