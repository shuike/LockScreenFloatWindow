package com.skit.lockfloatview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt).setOnClickListener {
            if (MAccessibilityService.mAccessibilityService == null) {
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(this)
                }
            } else {
                showFloatWindow()
            }
        }
    }

    /**
     * 显示悬浮窗
     */
    private fun showFloatWindow() {
        val mAccessibilityService = MAccessibilityService.mAccessibilityService
        if (mAccessibilityService == null) {
            Toast.makeText(this, "无障碍未开启", Toast.LENGTH_SHORT).show()
            return
        }
        // 使用无障碍的上下文获取 WindowManager
        val windowManager: WindowManager =
            mAccessibilityService.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val params = WindowManager.LayoutParams()
        // type需要使用无障碍的Type，系统API版本不能低于22
        params.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        val button = Button(this).apply {
            text = "Hello"
        }
        windowManager.addView(button, params)
    }

    override fun onResume() {
        super.onResume()
        // 无障碍服务启用状态更新
        title = "无障碍服务是否已启用: ${(MAccessibilityService.mAccessibilityService != null)}"
    }
}