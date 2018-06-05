package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_item_search.*

class ItemSearchActivity : AppCompatActivity()
{
    var display = 20;
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)

        val listView = findViewById(R.id.searchListView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        listView.adapter = adapter

        //searchView 설정 (참고사이트 : https://stackoverflow.com/questions/47303819/kotlin-how-to-get-searchview-submit)
        searchView.setSubmitButtonEnabled(true)
        searchView.isSubmitButtonEnabled()
        //검색창을 누르면 보이는 (값 입력시 사라짐)
        val seachViewText: String = getString(R.string.serchViewText)
        searchView.setQueryHint(seachViewText)


        //searchView 검색 버튼을 눌렀을때 발생하는 event
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
                    thread.join()// API 요청 후 데이터를 다 가져올때까지 대기 반드시 사용할 것
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                }
                var result = thread.getResult()
                //println("ItemSearch " + result)
                //쓰레드 처리 끝
                var title:Array<String>? = null
                title = thread.getTitle()
                var titles = thread.getTitle()
                println("test"+title)
                println("test"+titles)

                var i =0
                for (index in title)
                {
                    i++
                    //println((index+1) + " "+title[index])
                    print(i)
                    println("번째 상품명: ${index}")

                }
                return false
            }
        })

        //아이템 클릭시 화면이동
        searchListView.setOnItemClickListener { _, _, _, _ ->
            val intent = Intent(this, ShowMoreItemInfoActivity::class.java)
            startActivity(intent)
        }

    }
}
