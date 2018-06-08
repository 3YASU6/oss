package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_search.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore




class ItemSearchActivity : AppCompatActivity() {
    var display = 20;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)
        //     val db = FirebaseFirestore.getInstance()


//        val user = HashMap<String, Any>()
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);

        //     db.collection("users").add(user)


        // val database = FirebaseDatabase.getInstance()
        //  val myRef = database.getReference("message")

        //myRef.setValue("Hello, World!")

        val listView = findViewById(R.id.searchListView) as ListView
        var data_array_items = Array(20, { i -> "Title-$i" })
        var data_array_iprice = Array(20, { i -> "Price-$i" })
        var data_array_mallname = Array(20, { i -> "Mallname-$i" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        listView.adapter = adapter

        //searchView 설정 (참고사이트 : https://stackoverflow.com/questions/47303819/kotlin-how-to-get-searchview-submit)
        searchView.setSubmitButtonEnabled(true)
        searchView.isSubmitButtonEnabled()
        //검색창을 누르면 보이는 (값 입력시 사라짐)
        val seachViewText: String = getString(R.string.serchViewText)
        searchView.setQueryHint(seachViewText)


        //searchView 검색 버튼을 눌렀을때 발생하는 event
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                //검색어 입력시 : onQueryTextChange
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //검색어 완료시 : onQueryTextSubmit
                //Task HERE

                //thread 만들어서 불러옴 http://plaboratory.org/archives/108 참조
                val thread = Naver_API(query)
                thread.start()
                try {
                    thread.join()// API 요청 후 데이터를 다 가져올때까지 대기 반드시 사용할 것
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                var result = thread.getResult()
                //println("ItemSearch " + result)
                //쓰레드 처리 끝
                var title: Array<String>? = null
                title = thread.getTitle()

                var iprice: Array<String>? = null
                iprice = thread.getIprice()

                var mallname: Array<String>? = null
                mallname = thread.getMallName()

                //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html
                //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html
                //http://codetravel.tistory.com/17
                /*
                for (index in title)
                {
                    println("\n "+index+" 번째 상품명: ${index}")
                    data_array_items.set()
                }*/

                for ((index, value) in title.withIndex()) {
                    data_array_items.set(index, value)
                    println("the element at $index is $value")
                }
                for ((index, value) in iprice.withIndex()) {
                    data_array_iprice.set(index, value)

                }
                for ((index, value) in mallname.withIndex()) {
                    data_array_iprice.set(index, value)

                }
                val fix: Array<String>? = title
                return false
            }
        })

        //아이템 클릭시 화면이동
        searchListView.setOnItemClickListener {parent, view, position, id ->
            adapter.getItem(position)
            data_array_items.get(position)
            val a: String = data_array_items.get(position)
            val iprice: String = data_array_iprice.get(position)
            val mallname: String = data_array_mallname.get(position)
            Toast.makeText(this, "Position Clicked:"+" "+a,Toast.LENGTH_SHORT).show()
            val detailintent = Intent(this, ShowMoreItemInfoActivity::class.java)
            detailintent.putExtra("title", a);
            detailintent.putExtra("iprice", iprice);
            detailintent.putExtra("mallname", mallname);
            startActivity(detailintent)
        }


//            val intent = Intent(this, ShowMoreItemInfoActivity::class.java)
//            startActivity(intent)
    }

}