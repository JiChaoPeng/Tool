package com.android.frameworktool.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.frameworktool.R
import com.android.frameworktool.util.setStatusBarByView

/**
 * Created by jichaopeng
 * 2020-02-27
 */
open class BaseActivity : AppCompatActivity() {

    private var enterAnim: Int = R.anim.activity_common_show_by_end_anim
    private var exitAnim: Int? = R.anim.activity_common_end_anim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPendingTransition(R.anim.activity_common_show_by_end_anim, R.anim.activity_common_end_anim)
        setStatusBarByView()
    }

    override fun finish() {
        super.finish()
        if (exitAnim != null) {
            overridePendingTransition(enterAnim, exitAnim!!)
        }
    }

    private fun setPendingTransition(enterAnim: Int, exitAnim: Int?) {
        this.enterAnim = enterAnim
        this.exitAnim = exitAnim
    }
}