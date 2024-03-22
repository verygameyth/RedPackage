package com.nsyw.base.base

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsyw.base.widget.load.LoadModel
import com.nsyw.base.widget.load.LoadState

open class BaseViewModel() : ViewModel() {

    private val _loadState: MutableLiveData<LoadModel> =
        MutableLiveData(LoadModel(LoadState.LOAD_HIDE))
    val loadState: LiveData<LoadModel> = _loadState


    private val _toastMsg: MutableLiveData<String> = MutableLiveData("")
    val toastMsg: LiveData<String> = _toastMsg

    fun showLoading(msg: String?) {
        _loadState.postValue(LoadModel(state = LoadState.LOAD_SHOW, msg = msg))
    }

    fun hideLoading() {
        _loadState.postValue(LoadModel(state = LoadState.LOAD_HIDE))
    }

    fun showToast(msg: String?) {
        if (!msg.isNullOrBlank()) {
            _toastMsg.postValue(msg)
        }
    }

    open fun getApplication():BaseApp{
        return BaseApp.getApp()
    }
}