package com.example.kimhy.open_source_project_naver_price_compare

/*
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_itemlist.*

=======
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
>>>>>>> 72594f7f731029be579f33a5ec72f57cdac745df

class ItemList : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemlist)

        val listView = findViewById(R.id.listView) as ListView
        //val dataArray = arrayOf("yano","suhi","sai","kou","yana")
        val data_array_items = Array(20, { i -> "Title-$i" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        listView.adapter = adapter

        // item click시 발생하는 event를 추가
        listView.setOnItemClickListener { adapterView, view, position, id ->
            // Toast 표시
            val textView = view.findViewById<TextView>(android.R.id.text1)
            Toast.makeText(this, "Clicked: ${textView.text}", Toast.LENGTH_SHORT).show()

            // activity_graph 화면에 이동
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)

        }

        val hoge = "yanosuhi"
        // add button click시 발생하는 event를 추가
        addButton.setOnClickListener {
            //Log.v(hoge, "clicked")

            // activity_graph 화면에 이동
            val intent = Intent(this, ItemSearchActivity::class.java)
            startActivity(intent)
        }

    }
    fun onClick(view:View?) {
        val intent = Intent(this, ItemSearchActivity::class.java)
        startActivity(intent)
    }


}

        */