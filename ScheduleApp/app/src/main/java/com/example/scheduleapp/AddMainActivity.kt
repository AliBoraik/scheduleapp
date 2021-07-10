package com.example.scheduleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_add_main.*

class AddMainActivity : AppCompatActivity() {

    private var etName: EditText?= null
    private var etDateTo: EditText?= null
    private var etDateFrom: EditText?= null

    private var day:String? = null
    private var idItem:Int? = null

    private val editLessonRequestCode: Int = 2

    private var requestCode: Int? = null

    private var id: Int? = null


    var days:Array<String> = arrayOf("Sunday","Monday","Thursday","Wednesday","Thursday","Friday","Saturday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_main)



        val actionBar = supportActionBar!!
        val enterYourNote: Int = R.string.add_the_text
        actionBar.setTitle(enterYourNote)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)


        etName=findViewById(R.id.name)
        etDateFrom=findViewById(R.id.dateFrom)
        etDateTo=findViewById(R.id.dateTo)

        requestCode = intent.getIntExtra("requestCode", -1)




        etDateFrom?.setOnClickListener{
            showTimePickerDialog(etDateFrom!!)
        }
        etDateTo?.setOnClickListener{
            showTimePickerDialog(etDateTo!!)
        }

        val arrayAdapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,days)
        spinner.adapter = arrayAdapter2



        if (requestCode == editLessonRequestCode) {

            id = intent.getIntExtra("id", -1)
            etName?.setText(intent.getStringExtra("name"))
            day = intent.getStringExtra("day")
            idItem = intent.getIntExtra("dayOrder", -1)
            etDateFrom?.setText(intent.getStringExtra("dateFrom"))
            etDateTo?.setText(intent.getStringExtra("dateTo"))

            spinner.setSelection(idItem!! -1)

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day= days[position]
                idItem = position + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


    }

    private fun showTimePickerDialog(et: EditText) {
        val timePicker1 =
                MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(8)
                        .setMinute(30)
                        .setTitleText("Select Appointment time")
                        .build()


        timePicker1.show(supportFragmentManager,"tag")
        timePicker1.addOnPositiveButtonClickListener {
            val time ="${timePicker1.hour}-${timePicker1.minute}"
            et.setText(time)
        }
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
                if (checkInformation()) {

                    val intent = Intent()
                    intent.putExtra("idItem", idItem)
                    intent.putExtra("name", etName?.text.toString())
                    intent.putExtra("day", day)
                    intent.putExtra("dateFrom", etDateFrom?.text.toString())
                    intent.putExtra("dateTo", etDateTo?.text.toString())

                    if (requestCode == editLessonRequestCode ) {
                        intent.putExtra("id", id)
                    }

                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Enter all fields please", Toast.LENGTH_SHORT).show()
                }

            }

            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                this.finish()
            }
        }


        return super.onOptionsItemSelected(item)
    }
    private fun checkInformation(): Boolean {
        val info = BooleanArray(4)
        if (etName?.text?.isEmpty() == false) {
            info[0] = true
        }
        if (day  != null) {
            info[1] = true
        }
        if (etDateFrom?.text?.isEmpty() == false) {
            info[2] = true
        }
        if (etDateTo?.text?.isEmpty() == false) {
            info[3] = true
        }
        for (b in info) {
            if (!b) {
                return false
            }
        }
        return true
    }
}