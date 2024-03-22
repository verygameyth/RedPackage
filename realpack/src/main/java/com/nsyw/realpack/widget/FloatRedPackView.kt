package com.nsyw.realpack.widget

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.nsyw.realpack.R

class FloatRedPackView(context: Context) : FrameLayout(context) {

    private val TAG = FloatRedPackView::class.java.simpleName

    //
    private var view: View
    private var mLastRawX: Float = 0F
    private var mLastRawY: Float = 0F
    private var isDrag = false
    private var mRootMeasuredWidth = 0
    private var mRootMeasuredHeight = 0


    init {
//        val lp = WindowManager.LayoutParams().apply {
//            width = DisplayUtil.dpToPx(context, 60f)
//            height = DisplayUtil.dpToPx(context, 60f)
//        }
        view = LayoutInflater.from(context).inflate(R.layout.red_float, null)
        addView(view)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let {
            val location = IntArray(2)
            getLocationInWindow(location)
            val startX = location[0].toFloat()
            val startY = location[1].toFloat()
            val endX = (startX + width).toFloat()
            val endY = (startY + height).toFloat()
            val fX = it.rawX
            val fY = it.rawY
            val parentView = parent

            Log.d(TAG, "parentView:${parentView is ViewGroup}")

        }
//        var intercept = false
//        ev?.let {
//            val location = IntArray(2)
//            getLocationInWindow(location)
//            val startX = location[0].toFloat()
//            val startY = location[1].toFloat()
//            val endX = (startX + width).toFloat()
//            val endY = (startY + height).toFloat()
//            val fX = it.rawX
//            val fY = it.rawY
//            intercept = when (it.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    if (!isDrag) {
//                        (fX in startX..endX && fY in startY..endY)
//                    } else {
//                        true
//                    }
//                }
//
//                MotionEvent.ACTION_MOVE -> {
//                    isDrag
//                }
//
//                else -> {
//                    super.onInterceptTouchEvent(ev)
//                }
//            }
//        }
//        Log.d(TAG, "onInterceptTouchEvent():${intercept}  isDrag:${isDrag}")
//        return intercept
        return true
    }


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        event?.let {
//            //判断是否需要滑动
//            //当前手指的坐标
//            val mRawX = it.rawX
//            val mRawY = it.rawY
//            when (it.action) {
//                MotionEvent.ACTION_DOWN -> {//手指按下
//                    isDrag = false
//                    //记录按下的位置
//                    mLastRawX = mRawX
//                    mLastRawY = mRawY
//                    //获取父布局的高度
//                    val parentView=this.parent
//                    if(parentView is ViewGroup){
//                        mRootMeasuredHeight = parentView.measuredHeight
//                        mRootMeasuredWidth = parentView.measuredWidth
//                    }
//
//                    //获取父布局顶点的坐标
////                    Log.e(TAG, "width:${mRootMeasuredWidth}  height:${mRootMeasuredHeight}")
//
//                }
//
//                MotionEvent.ACTION_MOVE -> {//手指滑动
//                    val location = IntArray(2)
//                    getLocationInWindow(location)
//                    if (mRawX in location[0].toFloat()..(location[0] + view.width).toFloat() && mRawY in location[1].toFloat()..(location[1] + view.height).toFloat()) {
//                        //手指X轴滑动距离
//                        val differenceValueX: Float = mRawX - mLastRawX
//                        //手指Y轴滑动距离
//                        val differenceValueY: Float = mRawY - mLastRawY
//                        //判断是否为拖动操作
//                        if (!isDrag) {
//                            isDrag =
//                                sqrt(((differenceValueX * differenceValueX) + (differenceValueY * differenceValueY)).toDouble()) >= 2
//                        }
//                        //获取手指按下的距离与控件本身X轴的距离
//                        val ownX = view.x
//                        //获取手指按下的距离与控件本身Y轴的距离
//                        val ownY = view.y
//                        //理论中X轴拖动的距离
//                        var endX: Float = ownX + differenceValueX
//                        //理论中Y轴拖动的距离
//                        var endY: Float = ownY + differenceValueY
//                        //X轴可以拖动的最大距离
//                        val maxX: Float = mRootMeasuredWidth - view.width.toFloat()
//                        //Y轴可以拖动的最大距离
//                        val maxY: Float = mRootMeasuredHeight - view.height.toFloat()
////                        Log.d(
////                            TAG,
////                            "isDrag:${sqrt(((differenceValueX * differenceValueX) + (differenceValueY * differenceValueY)).toDouble()) >= 2}  " +
////                                    "mRawX:${mRawX}  " +
////                                    "mRawY:${mRawY}  " +
////                                    "mLastRawX:${mLastRawX}  " +
////                                    "mLastRawY:${mLastRawY} " +
////                                    "endX:${endX} " +
////                                    "endY:${endY} "
////                        )
//                        //X轴边界限制
//                        endX = if (endX < 0) 0F else (if (endX > maxX) maxX else endX)
//                        //Y轴边界限制
//                        endY = if (endY < 0) 0F else (if (endY > maxY) maxY else endY)
//                        //开始移动
//                        view.x = endX
//                        view.y = endY
//                        //记录位置
//                        mLastRawX = mRawX
//                        mLastRawY = mRawY
//                        Log.d(TAG, "viewX:${endX}  viewY:${endY}")
//                    } else {
//
//                    }
//                }
//
//                MotionEvent.ACTION_UP -> {//手指离开
//
//                    //判断是否为点击事件
//                    if (isDrag) {
//                        val center = mRootMeasuredWidth / 2
//                        //自动贴边
//                        if (mLastRawX <= center) {
//                            //向左贴边
//                            view.animate()
//                                .setInterpolator(BounceInterpolator())
//                                .setDuration(500)
//                                .x(0F)
//                                .start()
//                        } else {
//                            //向右贴边
//                            view.animate()
//                                .setInterpolator(BounceInterpolator())
//                                .setDuration(500)
//                                .x(mRootMeasuredWidth - view.width.toFloat())
//                                .start()
//                        }
//                        isDrag = false
//                    } else {
//
//                    }
//                }
//
//                else -> {}
//            }
//
//
//        }
//        //是否拦截事件
//        return if (isDrag) isDrag else super.onTouchEvent(event)
//    }
}