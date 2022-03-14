package com.skit.lockfloatview

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class MAccessibilityService : AccessibilityService() {

    companion object {
        var mAccessibilityService: MAccessibilityService? = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mAccessibilityService = null
    }

    override fun onCreate() {
        super.onCreate()
        mAccessibilityService = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

    }

    override fun onInterrupt() {

    }
}