package com.akib.kotlinuibasics

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : BaseActivity() {

    val TEXT_HOLDER: String = "text holder"
    private var edtText: EditText? = null
    private var txtText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnClick: Button = findViewById(R.id.btn_click)
        edtText = findViewById(R.id.edt_text)
        txtText = findViewById(R.id.txt_clicks)
        txtText?.movementMethod = ScrollingMovementMethod()
        txtText?.append("txtText?.movementMethod = ScrollingMovementMethod()")
        btnClick.setOnClickListener {
            if (edtText?.text.isNullOrEmpty()) {
                edtText?.error = "Enter Text to add in below list"
            } else {
                txtText?.append("\n ${edtText?.text} ")
                edtText?.setText("")
                edtText?.error = null
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_HOLDER, txtText?.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        txtText?.text = savedInstanceState.getString(TEXT_HOLDER)
    }
}