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


class ItemSearchActivity : AppCompatActivity()
{
    var display = 20

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)

        val listView = findViewById(R.id.searchListView) as ListView
        var data_array_items = Array(display, { i -> "Title-$i" })
        var data_array_iprice = Array(display, { i -> "Price-$i" })
        var data_array_mallname = Array(display, { i -> "Mallname-$i" })
        var data_array_link = Array(display, { i -> "Link-$i" })
        var data_array_image = Array(display, { i -> "Image-$i" })
        var data_array_hprice = Array(display, { i -> "hprice-$i" })
        var data_array_productid = Array(display, { i -> "productid-$i" })


        for(i in data_array_items.indices){
            data_array_items.set(i,"")
        }

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
                //쓰레드 처리 끝
                var title: Array<String>? = null
                title = thread.getTitle()

                var iprice: Array<String>? = null
                iprice = thread.getIprice()

                var mallname: Array<String>? = null
                mallname = thread.getMallName()

                var link: Array<String>? = null
                link = thread.getLink()

                var image: Array<String>? = null
                image = thread.getImage()

                var hprice: Array<String>? = null
                hprice = thread.getHprice()

                var productid: Array<String>? = null
                productid = thread.getProduceId()
                println(productid)

                //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html
                //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html

                for (index in productid)
                {
                    println("\n "+index+" 번째 상품명: ${index}")
                }

                for ((index, value) in title.withIndex())
                {
                    data_array_items.set(index, value)
                    println("the element at $index is $value")
                }
                for ((index, value) in iprice.withIndex())
                {
                    data_array_iprice.set(index, value)
                }
                for ((index, value) in mallname.withIndex())
                {
                    data_array_mallname.set(index, value)
                }
                for ((index, value) in link.withIndex())
                {
                    data_array_link.set(index, value)
                }
                for ((index, value) in image.withIndex())
                {
                    data_array_image.set(index, value)
                }
                for ((index, value) in hprice.withIndex())
                {
                    data_array_hprice.set(index, value)
                    println("the element at $index is $value")
                }
                for ((index, value) in productid.withIndex())
                {
                    data_array_productid.set(index, value)
                    println("the element at $index is $value")
                }

                val fix: Array<String>? = title
                return false
            }
        })

        //아이템 클릭시 화면이동
        searchListView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)
            data_array_items.get(position)
            val title_sub: String = data_array_items.get(position)
            val lprice: String = data_array_iprice.get(position)
            val mallname: String = data_array_mallname.get(position)
            val image: String = data_array_image.get(position)
            val hprice: String = data_array_hprice.get(position)
            val link_: String = data_array_link.get(position)
            val productid_: String = data_array_productid.get(position)
            Toast.makeText(this, "Position Clicked:" + " " + title_sub, Toast.LENGTH_SHORT).show()

            val detailintent = Intent(this, ShowMoreItemInfoActivity::class.java)

            //=========prepare the text, so it can be taken in ShowMoreItemInfo
            detailintent.putExtra("title", title_sub)
            detailintent.putExtra("lprice", lprice)
            detailintent.putExtra("mallname", mallname)
            detailintent.putExtra("image", image)
            detailintent.putExtra("hprice", hprice)
            detailintent.putExtra("link", link_)
            detailintent.putExtra("productid", productid_)
            startActivity(detailintent)
        }
    }
}