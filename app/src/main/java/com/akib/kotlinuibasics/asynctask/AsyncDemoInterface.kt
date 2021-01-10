package com.akib.kotlinuibasics.asynctask

interface AsyncDemoInterface {
    fun onSuccess(records: ArrayList<FeedEntity>)
    fun onFail(message: String)
}