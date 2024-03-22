package com.nsyw.base.widget.load

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.annotation.Keep
import androidx.fragment.app.FragmentManager
import com.nsyw.base.base.BaseDialogFragment
import com.nsyw.base.databinding.BaseDialogLoadBinding
import com.nsyw.base.route.RouteManager

class LoadDialog : BaseDialogFragment() {

    private val viewModel: LoadViewModel by lazy {
        getViewModel()
    }

    private val binding by lazy {
        BaseDialogLoadBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 1000
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.repeatCount = Animation.INFINITE
        binding.ivLoad.startAnimation(rotateAnimation)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    companion object {
        fun showDialog(params: String?, fragmentManager: FragmentManager): LoadDialog {
            return LoadDialog().apply {
                arguments = Bundle().apply {
                    putString(RouteManager.PARAMS, params)
                }
                show(fragmentManager, "")
            }
        }
    }

    @Keep
    data class LoadDialogDto(val loadText: String)

}