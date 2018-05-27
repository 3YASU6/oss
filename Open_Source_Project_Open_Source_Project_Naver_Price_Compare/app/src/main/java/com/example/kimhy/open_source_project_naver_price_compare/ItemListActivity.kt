package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_list.*


class ItemListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

    /*    val thisView = findViewById(R.id.listView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        thisView.adapter = adapter

        // item click시 발생하는 event를 추가
        thisView.setOnItemClickListener { _, view, _, _ ->
            // Toast 표시
            val textView = view.findViewById<TextView>(android.R.id.text1)
            Toast.makeText(this, "Clicked: ${textView.text}", Toast.LENGTH_SHORT).show()

            // activity_graph 화면에 이동
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)

        }

        // add button click시 발생하는 event를 추가
        itemButton.setOnClickListener {
            val intent = Intent(this, ItemSearchActivity::class.java)
            startActivity(intent)

        }*/
    }
}
