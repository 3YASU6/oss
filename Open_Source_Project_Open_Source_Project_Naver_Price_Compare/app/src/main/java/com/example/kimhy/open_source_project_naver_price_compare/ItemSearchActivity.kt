package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_item_search.*
import kotlinx.android.synthetic.main.activity_item_search.view.*

class ItemSearchActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)

        val listView = findViewById(R.id.searchListView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        listView.adapter = adapter

        searchView.setSubmitButtonEnabled(true)
        searchView.isSubmitButtonEnabled()
        //검색창을 누르면 보이는 값 입력시 사라짐
        val seachViewText: String = getString(R.string.serchViewText)
        searchView.setQueryHint(seachViewText) //검색어를 입력하세요")

        //https://stackoverflow.com/questions/47303819/kotlin-how-to-get-searchview-submit

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {

            override fun onQueryTextChange(newText: String): Boolean
            {
                //검색어 입력시 : onQueryTextChange
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean
            {
                //검색어 완료시 : onQueryTextSubmit
                //Task HERE

                //thread 만들어서 불러옴 http://plaboratory.org/archives/108 참조
                val thread = Naver_API(query)
                thread.start()
                try
                {
                    thread.join()// API 요청 후 데이터를 다 가져올때까지 대기
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                }
                var result = thread.getResult()
                println("ItemSearch " + result)
                //쓰레드 처리 끝

                return false
            }

        })




        searchListView.setOnItemClickListener { _, _, _, _ ->

            val intent = Intent(this, ShowMoreItemInfoActivity::class.java)
            startActivity(intent)

        }


    }
}
