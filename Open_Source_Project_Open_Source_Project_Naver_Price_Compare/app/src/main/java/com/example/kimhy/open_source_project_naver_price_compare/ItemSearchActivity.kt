package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_item_search.*
import kotlinx.android.synthetic.main.activity_item_search.view.*

class ItemSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)

        val listView = findViewById(R.id.searchListView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        listView.adapter = adapter

        //검색창을 누르면 보이는 값 입력시 사라짐
        searchView.setQueryHint("검색어를 입력하세요")

        //https://stackoverflow.com/questions/47303819/kotlin-how-to-get-searchview-submit

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                //검색어 입력시 : onQueryTextChange
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //검색어 완료시 : onQueryTextSubmit
                //Task HERE
                println("Hello World!")
                var apiCall = Naver_API()
                var printString = apiCall.naver_API_Call(query)
                println(printString)

                return false
            }

        })




        searchListView.setOnItemClickListener { _, _, _, _ ->

            val intent = Intent(this, ShowMoreItemInfoActivity::class.java)
            startActivity(intent)

        }


    }
}
