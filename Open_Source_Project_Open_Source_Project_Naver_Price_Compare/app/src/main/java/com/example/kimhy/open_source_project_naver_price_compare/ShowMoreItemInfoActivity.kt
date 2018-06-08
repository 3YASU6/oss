package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_show_more_item_info.*

class ShowMoreItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_more_item_info)

        val intent = intent
        val bd = intent.extras
        if (bd != null) {
            val getName = bd.get("title") as String
            tileText.setText(getName)
            val getiprice = bd.get("iprice") as String
            lpriceText.setText(getiprice)
            val getmallname = bd.get("mallname") as String
            mallNameText.setText(getmallname)
//            val getimage = bd.get("image") as String
     //       itemImage.setImageURI(getimage)
         //   Picasso.get().load(url).into(view);

        }
//        itemImage.seton(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//                //검색어 입력시 : onQueryTextChange
//                return false
//            }
//        override fun onQueryTextSubmit(query: String): Boolean {
//            //검색어 완료시 : onQueryTextSubmit
//            //Task HERE
//
//            //thread 만들어서 불러옴 http://plaboratory.org/archives/108 참조
//            val thread = Naver_API(query)
//            thread.start()
//            try {
//                thread.join()// API 요청 후 데이터를 다 가져올때까지 대기 반드시 사용할 것
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            var result = thread.getResult()
//            //println("ItemSearch " + result)
//            //쓰레드 처리 끝
//            var title: Array<String>? = null
//            title = thread.getTitle()
//
//            //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html
//            //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html
//            //http://codetravel.tistory.com/17
//            /*
//            for (index in title)
//            {
//
//                println("\n "+index+" 번째 상품명: ${index}")
//                data_array_items.set()
//            }*/
//
//            for ((index, value) in title.withIndex()) {
//                data_array_items.set(index, value)
//                println("the element at $index is $value")
//            }
//
//            return false
//        }
//    })
        // addToWishListButton click시 발생하는 event를 추가
        addToWishListButton.setOnClickListener {
            val intent = Intent(this, ItemListActivity::class.java)
            intent.putExtra("title", tileText.getText());
            intent.putExtra("iprice", lpriceText.getText());
            intent.putExtra("mallname",  mallNameText.getText());

            startActivity(intent)
        }


    }
}
