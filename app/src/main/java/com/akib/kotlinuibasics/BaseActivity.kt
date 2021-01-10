package com.akib.kotlinuibasics

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.akib.kotlinuibasics.asynctask.AsyncDemoActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.program_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mu_activity_life_cycle -> {
                startActivity(Intent(this, ActivityLifeCycleActivity::class.java))
            }
            R.id.mu_enter_text_program -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.mu_async_program -> {
                startActivity(Intent(this, AsyncDemoActivity::class.java))
            }
        }
        return true
    }
}