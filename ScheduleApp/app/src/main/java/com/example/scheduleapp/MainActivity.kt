package com.example.scheduleapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CustomRecyclerAdapter.OnItemC {
    private var listLessens: ArrayList<Lessen>? =null


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter =CustomRecyclerAdapter(getList(),this)


        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)

        fab.setOnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, AddMainActivity::class.java)
            startActivityForResult(intent, 1)
        }


    }
    private fun getList(): List<Lessen> {
        listLessens = ArrayList()

        val l =Lessen("Mathematical",getColor(),"saturday", "02-07")
        val l1 =Lessen("Mathematical",getColor(),"saturday", "02-07")
        val l2 =Lessen("Mathematical",getColor(),"saturday", "02-07")
        val l3 =Lessen("Mathematical",getColor(),"saturday", "02-07")
        val l4 =Lessen("Mathematical",getColor(),"saturday", "02-07")
        val l5 =Lessen("Mathematical",getColor(),"saturday", "02-07")
        val l6 =Lessen("Mathematical",getColor(),"saturday", "02-07")

        listLessens?.add(l)
        listLessens?.add(l1)
        listLessens?.add(l2)
        listLessens?.add(l3)
        listLessens?.add(l4)
        listLessens?.add(l5)
        listLessens?.add(l6)

            return listLessens!!.toList()
    }


    private fun getColor(): Int {
        val rnd = Random()
        return Color.argb(200, rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200))
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(applicationContext, "$position  removeMode", Toast.LENGTH_SHORT).show()
    }
}



private fun fillList(): List<String> {
    val data = mutableListOf<String>()
    (0..30).forEach { i -> data.add("\$i element") }
    return data
}
