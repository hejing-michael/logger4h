package com.slf4j.logger

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*

class TestActivity : Activity() {
    val TAG = "MainActivity"
    val loggerList = Arrays.asList(
            LoggerFactory.getLogger("Main_aa"),
            LoggerFactory.getLogger("Main_bb"),
            LoggerFactory.getLogger("Main_cc"),
            LoggerFactory.getLogger("Main_dd"),
            LoggerFactory.getLogger("Main_ee"),
            LoggerFactory.getLogger("Main_ff"),
            LoggerFactory.getLogger("Main_gg"),
            LoggerFactory.getLogger("Main_hh"),
            LoggerFactory.getLogger("Main_ii"),
            LoggerFactory.getLogger("Main_jj"),
            LoggerFactory.getLogger("Main_kk")
    )
    var etContent: EditText? = null
    var etThread: EditText? = null
    var btnWrite: Button? = null
    var btnTest: Button? = null
    var tvTest: TextView? = null
    var etTimes: EditText? = null
    val testing = false

    open fun log(message: String) {
        for (logger in loggerList) {
            logger.info(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        etThread = findViewById<EditText>(R.id.et_thread)
        etContent = findViewById<EditText>(R.id.et_content)
        btnWrite = findViewById<Button>(R.id.btn_write)
        btnTest = findViewById<Button>(R.id.btn_test)
        tvTest = findViewById<TextView>(R.id.tv_test)
        etTimes = findViewById<EditText>(R.id.et_times)
        btnWrite!!.setOnClickListener {
            val content = etContent!!.text.toString()
            for (i in 0..999) {
                val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault()).format(Date())
                log("log-->$time-->$i :$content")
            }
        }
    }
}
