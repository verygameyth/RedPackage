package com.nsyw.base.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nsyw.base.utils.ToastUtil
import com.nsyw.base.widget.load.LoadState

abstract class BaseDialogFragment:DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel= ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel.loadState.observe(this) {
            when (it.state) {
                LoadState.LOAD_SHOW -> {

                }

                else -> {

                }
            }
        }
        viewModel.toastMsg.observe(this) {
            context?.let { cxt->
                ToastUtil.showToast(cxt, it)
            }
        }
    }

    inline fun<reified T:BaseViewModel> getViewModel():T{
        return ViewModelProvider(this)[T::class.java]
    }
}