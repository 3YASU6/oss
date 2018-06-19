package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.activity_show_more_item_info.*
import retrofit2.http.Url
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

//import jdk.nashorn.internal.objects.NativeDate.getTime
import java.util.*



class ShowMoreItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_more_item_info)

        // 상품 데이터
        val itemName = intent.getStringExtra("title")
        val lPrice = intent.getStringExtra("lprice")
        val mall_name = intent.getStringExtra("mallname")
        val link = intent.getStringExtra("link")
        val productid = intent.getStringExtra("productid")
        val image = intent.getStringExtra("image")

        // textView Setting
        tileText.setText(itemName)
        lpriceText.setText(lPrice)
        mallNameText.setText(mall_name)
        productidText.setText(productid)
        linkText.setText(link)
        imageurl.loadUrl(image)

        // 상품정보를 한줄에 넣기
        val item_info = itemName + "," + link + "," + lPrice + "," + productid + "," + mall_name + ","

        // addToWishListButton click 시 발생하는 event
        addToWishListButton.setOnClickListener {
            // file(items.txt) 에 상품 정보 추가
            val fileio = FileIO(this)
            val result_message = fileio.addNewItem(item_info)

            // ItemList 화면에 이동
            val goToItemListIntent = Intent(this,ItemListActivity::class.java)
            startActivity(goToItemListIntent)
        }
    }
}
