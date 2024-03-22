package com.nsyw.base.widget.load

import androidx.fragment.app.FragmentManager
import com.nsyw.base.utils.toJson

object LoadDialogUtil {
    private var loadDialog: LoadDialog? = null

    fun showDialog(loadText: String?, fragmentManager: FragmentManager) {
        loadDialog = LoadDialog.showDialog(
            params = LoadDialog.LoadDialogDto(loadText = loadText?:"加载中").toJson(),
            fragmentManager = fragmentManager
        )
    }

    fun hideDialog() {
        if (loadDialog != null) {
            loadDialog?.dismiss()
            loadDialog = null
        }
    }
}