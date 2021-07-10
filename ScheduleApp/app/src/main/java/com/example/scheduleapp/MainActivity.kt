package com.example.scheduleapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.DataBase.AppDatabase
import com.example.scheduleapp.DataBase.employeeDao
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), CustomRecyclerAdapter.OnItemC {

    private var listData: MutableList<Lesson>? = null

    private var employeeDao: employeeDao? = null
    private var recyclerView: RecyclerView? = null

    private val addNewLessonRequestCode: Int = 1
    private val editLessonRequestCode: Int = 2

    private var tvWelcome: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvWelcome= findViewById(R.id.tvWelcome)

        val db: AppDatabase? = MyApplication.instance.getDatabase()
        employeeDao= db!!.employeeDao()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter =CustomRecyclerAdapter(getList() as List<Lesson>, this)

        fabList.setOnClickListener{

            val intent = Intent(this@MainActivity, AddMainActivity::class.java)
            intent.putExtra("requestCode", addNewLessonRequestCode)
            startActivityForResult(intent, addNewLessonRequestCode)


        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == addNewLessonRequestCode) {
            if (resultCode == Activity.RESULT_OK) {

                val e=Lesson()

                e.dayOrder = data!!.getIntExtra("idItem", 0)
                e.name= data!!.getStringExtra("name").toString()
                e.day= data.getStringExtra("day").toString()
                e.color= getColorForDay(e.dayOrder!!)
                e.dateFrom= data.getStringExtra("dateFrom").toString()
                e.dateTo= data.getStringExtra("dateTo").toString()

                employeeDao?.insert(e)
                refreshData()

            }
        }
        if (requestCode == editLessonRequestCode){
            if (resultCode == Activity.RESULT_OK){

                val e=Lesson()


                e.id = data!!.getIntExtra("id",0)
                e.dayOrder = data!!.getIntExtra("idItem", 0)
                e.name= data!!.getStringExtra("name").toString()
                e.day= data.getStringExtra("day").toString()
                e.color= getColorForDay(e.dayOrder!!)
                e.dateFrom= data.getStringExtra("dateFrom").toString()
                e.dateTo= data.getStringExtra("dateTo").toString()

                employeeDao?.update(e)
                refreshData()

            }
        }
    }

    private fun getColorForDay(day: Int): Int {
        return when (day) {
            1 -> Color.parseColor("#954A29")
            2 -> Color.parseColor("#37445c")
            3 -> Color.parseColor("#299e5e")
            4 -> Color.parseColor("#d5817f")
            5 -> Color.parseColor("#909a90")
            6 -> Color.parseColor("#ddbd8f")
            7 -> Color.parseColor("#bd9b48")
            else -> Color.parseColor("#2182c4")
        }
    }

    private fun getList(): List<Lesson?>? {
        listData = employeeDao?.all as MutableList<Lesson>?

        listData?.sortWith(compareBy<Lesson> { it.dayOrder }.thenBy{ it.dateFrom })
        tvWelcome?.isVisible = listData?.isEmpty() == true


        return listData
    }


    private fun refreshData() {

        recyclerView?.adapter =CustomRecyclerAdapter(getList() as List<Lesson>, this)


    }



    override fun onItemClick(position: Int,editMode:Boolean) {

        if (editMode){

            val intent = Intent(this@MainActivity, AddMainActivity::class.java)

            intent.putExtra("requestCode", editLessonRequestCode)
            intent.putExtra("id", listData?.get(position)?.id)
            intent.putExtra("name", listData?.get(position)?.name)
            intent.putExtra("dayOrder", listData?.get(position)?.dayOrder)
            intent.putExtra("day", listData?.get(position)?.day)
            intent.putExtra("dateFrom", listData?.get(position)?.dateFrom)
            intent.putExtra("dateTo", listData?.get(position)?.dateTo)

            startActivityForResult(intent, editLessonRequestCode)

        }else{
            Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this@MainActivity, AddMainActivity::class.java)
                intent.putExtra("requestCode", addNewLessonRequestCode)
                startActivityForResult(intent, addNewLessonRequestCode)

            }
        }


        return super.onOptionsItemSelected(item)
    }
}
