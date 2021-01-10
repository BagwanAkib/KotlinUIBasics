package com.akib.kotlinuibasics

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ActivityLifeCycleActivity : BaseActivity() {

    var activityMethodCalls: String = ""
    var previous: String = ""
    var txtActivities: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        txtActivities = findViewById(R.id.txt_activities)
        printOnUI("ON onCreate")
        (findViewById<Button>(R.id.btn_clear))?.setOnClickListener {
            printOnUI("")
            previous = activityMethodCalls
            previous += "\n =============================\n"
            printOnUI(previous)
            activityMethodCalls = ""
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        printOnUI("ON onAttachFragment")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        printOnUI("ON onContentChanged")
    }

    override fun onStart() {
        super.onStart()
        printOnUI("ON onStart")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        printOnUI("ON onActivityResult")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        printOnUI("ON onRestoreInstanceState")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        printOnUI("ON onPostCreate")
    }

    override fun onResume() {
        super.onResume()
        printOnUI("ON onResume")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        printOnUI("ON onAttachedToWindow")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        printOnUI("ON onSaveInstanceState")
    }
    override fun onPause() {
        super.onPause()
        printOnUI("ON onPause")
    }

    override fun onStop() {
        super.onStop()
        printOnUI("ON onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        printOnUI("ON onDestroy")
    }


    private fun printOnUI(methodName: String) {
        Log.d("ACTIVITY_LIFE_CYCLE", methodName)
        activityMethodCalls += "\n $methodName"
        txtActivities?.text = activityMethodCalls
    }
}
/*
* on Activity launched
2021-01-09 15:34:08.469 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onContentChanged
2021-01-09 15:34:08.469 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onCreate
2021-01-09 15:34:08.473 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStart
2021-01-09 15:34:08.474 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPostCreate
2021-01-09 15:34:08.474 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onResume
2021-01-09 15:34:08.487 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onAttachedToWindow

on move to phone menu
2021-01-09 15:34:40.008 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPause
2021-01-09 15:34:40.525 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStop
2021-01-09 15:34:40.532 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onSaveInstanceState

on Re-open app
2021-01-09 15:34:52.683 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStart
2021-01-09 15:34:52.696 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onResume

on Rotate
2021-01-09 15:35:14.308 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPause
2021-01-09 15:35:14.315 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStop
2021-01-09 15:35:14.320 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onSaveInstanceState
2021-01-09 15:35:14.323 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onDestroy
2021-01-09 15:35:14.404 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onContentChanged
2021-01-09 15:35:14.404 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onCreate
2021-01-09 15:35:14.411 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStart
2021-01-09 15:35:14.412 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onRestoreInstanceState
2021-01-09 15:35:14.413 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPostCreate
2021-01-09 15:35:14.413 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onResume
2021-01-09 15:35:14.426 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onAttachedToWindow

on Rotate normal
2021-01-09 15:35:43.005 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPause
2021-01-09 15:35:43.011 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStop
2021-01-09 15:35:43.014 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onSaveInstanceState
2021-01-09 15:35:43.017 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onDestroy
2021-01-09 15:35:43.082 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onContentChanged
2021-01-09 15:35:43.082 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onCreate
2021-01-09 15:35:43.087 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStart
2021-01-09 15:35:43.088 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onRestoreInstanceState
2021-01-09 15:35:43.089 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPostCreate
2021-01-09 15:35:43.089 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onResume
2021-01-09 15:35:43.101 5097-5097/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onAttachedToWindow

Kill Activity
2021-01-09 15:37:11.391 7051-7051/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onPause
2021-01-09 15:37:11.939 7051-7051/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onStop
2021-01-09 15:37:11.946 7051-7051/com.akib.kotlinuibasics D/ACTIVITY_LIFE_CYCLE: ON onDestroy
* */