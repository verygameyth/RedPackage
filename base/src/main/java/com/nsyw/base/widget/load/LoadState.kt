package com.nsyw.base.widget.load

class LoadModel(
    val state: LoadState,
    val msg: String? = null
)

enum class LoadState {
    LOAD_SHOW, LOAD_HIDE
}