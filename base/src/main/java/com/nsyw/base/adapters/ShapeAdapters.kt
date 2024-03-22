package com.nsyw.base.adapters

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.databinding.BindingAdapter
import com.nsyw.base.base.BaseApp
import com.nsyw.base.utils.DisplayUtil

@BindingAdapter(
    value = [
        "shape_bg_radius",
        "shape_solid_color",
        "shape_stroke_width",
        "shape_stroke_color"
    ],
    requireAll = false
)
fun backgroundShape(
    view: View,
    bgRadius: Float,
    bgColor: Long,
    strokeWidth: Float?,
    strokeColor: Int?
) {
    val drawable = GradientDrawable()
    drawable.setColor(bgColor.toInt())
    drawable.shape = GradientDrawable.RECTANGLE
    drawable.cornerRadius = DisplayUtil.dpToPx(BaseApp.getApp(), bgRadius).toFloat()
    if (strokeWidth != null && strokeWidth > 0 && strokeColor != null) {
        drawable.setStroke(DisplayUtil.dpToPx(BaseApp.getApp(), strokeWidth), strokeColor)
    }
    view.background = drawable
}
