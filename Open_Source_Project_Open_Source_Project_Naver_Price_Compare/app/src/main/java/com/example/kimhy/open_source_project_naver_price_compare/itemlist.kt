package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class ItemList : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemlist)

        val listView = findViewById(R.id.listView) as ListView
        val dataArray = arrayOf("yano", "suhi", "sai", "kou", "yana")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter
    }
    fun onClick(view : View)// search button -> ItemSearch Activity conversion
    {
        val itemSearchIntent = Intent(this, ItemSearch::class.java)
        startActivity(itemSearchIntent)
    }
}
