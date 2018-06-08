package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_list.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_show_more_item_info.*
import org.json.JSONException


class ItemListActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val thisView = findViewById(R.id.listView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })

        val intent = intent
        val bd = intent.extras

        if (bd != null)
        {
            val getName = bd.get("title") as String?
            for (i in 0 until data_array_items.size)
            {
                data_array_items[i]

            }
            //      tileText.setText(getName)
        }

        //   val values = arrayOf("Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Android", "iPhone", "WindowsMobile")

        val ti = intent.getStringExtra("title")


        val listView = ArrayList<String>()
        for (i in 0 until data_array_items.size)
        {
            if (i != 0)
            {

                data_array_items[i] == ti
//                listView.add(data_array_items[i])
                listView.add(data_array_items[i])

                i.inc()
            }
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        thisView.adapter = adapter
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("server/saving-data/fireblog")
        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")
        //  val users = ArrayList<User>()


        class product
        {

            var title: String? = null
            var iprice: String? = null
            var mallname: String? = null

            constructor(dateOfBirth: String, fullName: String)
            {
                // ...
            }

            constructor(dateOfBirth: String, fullName: String, nickname: String)
            {
                // ...
            }

        }

        //      val productRef = ref.child("product")
//        val intent = intent
//        val bd = intent.extras
//        val item = HashMap<String, Any>()


//        val gettitle = bd.get("title") as String
        //  listView.setText
        for (i in 0 until 20)
        {
            try
            {
                //         data_array_items.get()
            }
            catch (e: JSONException)
            {
                e.printStackTrace()
            }

        }

        //item.put("gracehop", User("December 9, 1906", "Grace Hopper"))

        // item click시 발생하는 event
        thisView.setOnItemClickListener { _, view, _, _ ->
            // activity_graph 화면에 이동
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }


        // add button click시 발생하는 event
        addButton.setOnClickListener {
            val intent = Intent(this, ItemSearchActivity::class.java)
            startActivity(intent)
        }


    }
}
