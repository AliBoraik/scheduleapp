package com.example.scheduleapp

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class AddMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_main)

        val actionBar = supportActionBar!!
        val enterYourNote: Int = R.string.add_the_text
        actionBar.setTitle(enterYourNote)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_settings).setIcon(R.drawable.ic_baseline_save_24)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {

            }

            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                this.finish()
            }
        }


        return super.onOptionsItemSelected(item)
    }
}