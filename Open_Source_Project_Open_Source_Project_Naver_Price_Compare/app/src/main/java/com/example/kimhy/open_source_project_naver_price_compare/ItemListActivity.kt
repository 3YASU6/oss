package com.example.kimhy.open_source_project_naver_price_compare

import android.content.ClipData
import android.content.Intent
import android.icu.util.ULocale
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_item_list.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_show_more_item_info.*
import org.json.JSONException
import kotlin.coroutines.experimental.EmptyCoroutineContext
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.DocumentSnapshot
//import jdk.nashorn.internal.runtime.ECMAException.getException
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import android.support.annotation.NonNull
import android.support.v7.widget.DefaultItemAnimator
import android.widget.*
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_item_search.*
import java.util.*
import kotlin.collections.ArrayList


class ItemListActivity : AppCompatActivity()
{

    lateinit var data_array_items: ArrayList<String>
    lateinit var data_array_iprice_: ArrayList<String>
    lateinit var data_array_mallname_: ArrayList<String>
    lateinit var data_array_link_: ArrayList<String>
    lateinit var data_array_image_: ArrayList<String>
    lateinit var data_array_date_: ArrayList<String>
    lateinit var data_array_hprice_: ArrayList<String>
    lateinit var data_array_productid_: ArrayList<String>
    lateinit var data_array_productType: ArrayList<String>
    lateinit var splitArray: ArrayList<String>
    lateinit var splitVariable: String

    lateinit var storeArray: ArrayList<String>

    /*
    data_array_items
    data_array_iprice_
    data_array_mallname_
    data_array_link_
    data_array_image_
    data_array_date_
    data_array_hprice_
    data_array_productid_
    */


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        // 화면에 표시 되는 상품 수
        val display = 20

        // db에 DB의 Instance 취득
        // val  db = FirebaseFirestore.getInstance()
        //.document("items")

        val count: Int
        val idnum: Int
        val name: String
        val price: String
        val mall: String
        val linkweb: String
        val i_price: String
        val mall_name: String
        val intent = intent
        val bd = intent.extras
        val ti = intent.getStringExtra("title")
        // ListView 를 취득
        val thisView = findViewById(R.id.listView) as ListView


        // ListView의 Item 에 놓는 데이터 배열를 초기화
        // var data_array_items = Array(display, { i -> "Title-$i" })
        // showMoreItemInfoActivity에서 온 intent값중 "title"를 취득
        val ItemName = intent.getStringExtra("title")
        val ItemLink = intent.getStringExtra("link")

        val ItemLowprice = intent.getStringExtra("iprice")
        val ItemMallname = intent.getStringExtra("mallname")
        val ItemDate = intent.getStringExtra("date")
        val ItemProductId = intent.getStringExtra("productid")

        var fileIO = FileIO(this)
        data_array_items = ArrayList<String>()
        data_array_iprice_ = ArrayList<String>()
        data_array_mallname_ = ArrayList<String>()
        data_array_link_ = ArrayList<String>()
        data_array_image_ = ArrayList<String>()
        data_array_date_ = ArrayList<String>()
        data_array_hprice_ = ArrayList<String>()
        data_array_productid_ = ArrayList<String>()
        storeArray = ArrayList<String>()

        splitArray = fileIO.loadItemsFromFile()
        println("splitArray " + splitArray)
        splitVariable = splitArray.toString()
        split()
        println("splitVariable : " + splitVariable)



        if (data_array_items.size == 0)// 오류 방지를 위한 초기값 셋팅 미 설정시   Caused by: kotlin.UninitializedPropertyAccessException: lateinit property data_array_items has not been initialized
        {
            data_array_items.add(0, "LG전자 올뉴그램 15ZD980-GX50K")
            data_array_link_.add(0, "http://search.shopping.naver.com/gate.nhn?id=13019218737")
      //      data_array_image_.add(0, "https://shopping-phinf.pstatic.net/main_1301921/13019218737.20171219161642.jpg")
            data_array_iprice_.add(0, "1349000")
            data_array_mallname_.add(0, "네이버")
            data_array_productid_.add(0, "13019218737")

            data_array_items.add(1, "삼성전자 오딧세이Z NT850XAX-GD7A")
            data_array_link_.add(1, "http://search.shopping.naver.com/gate.nhn?id=14265078551")
            //      data_array_image_.add(1, "https://shopping-phinf.pstatic.net/main_1426507/14265078551.20180516152958.jpg")
            data_array_iprice_.add(1, "1999000")
            data_array_mallname_.add(1, "네이버")
            data_array_productid_.add(1, "14265078551")
        }
        println("data_array_items t4esaf" + data_array_items)

//        data_array_items
//        data_array_iprice_
//        data_array_mallname_
//        data_array_link_
//        data_array_image_
//        data_array_date_
//        data_array_hprice_
//        data_array_productid_

        val swapVariable = intent.extras
        if (swapVariable != null)
        {
            val getLink = swapVariable.get("link") as String
            link.setText(getLink)
            linkweb = link.getText().toString()
            if (ItemName == null)
            {
                //data_array_items.add("lg gram notebook")

            }
            else
            {
                data_array_link_.add(linkweb)

            }
            val getiprice = swapVariable.get("iprice") as String
            link.setText(getiprice)
            i_price = link.getText().toString()
            if (ItemName == null)
            {
            }
            else
            {
                data_array_iprice_.add(i_price)

            }
            val gettitle = swapVariable.get("title") as String
            link.setText(gettitle)
            name = link.getText().toString()
            if (ItemName == null)
            {
            }
            else
            {
                data_array_items.add(name)

            }
        }

//        var data_array_link = ArrayList<String>()
//        var data_array_lprice = ArrayList<String>()
//        var data_array_mallname = ArrayList<String>()
        var data_array_productId = ArrayList<String>()
        var data_array_graphDate = ArrayList<String>()
        var data_array_graphPriceData = ArrayList<String>()


        //intent.putExtra("hprice",  hprice.getText());
        // ListView 첫번쩨 요소에 ItemName 추가


        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        listView.adapter = adapter


        //split
        // data_array_items = data_array_items.spliterator("\n")


        //var data_array_items = ArrayList<String>()
/*
        if (ItemName == null)
        {
            //data_array_items.add("lg gram notebook")

        }
        else
        {
            data_array_graphDate
            data_array_graphPriceData
            data_array_productId.add(ItemProductId)
        }
        */
        val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        thisView.adapter = itemsAdapter


        // item click시 발생하는 event
        thisView.setOnItemClickListener { parent, view, position, id ->
            // activity_graph 화면에 이동
            adapter.getItem(position)
            val position_: Int = position
            data_array_items.get(position)
            data_array_link_.get(position)
            data_array_iprice_.get(position)
            val title_sub: String = data_array_items.get(position)
            val link_: String = data_array_link_.get(position)
            val lprice: String = data_array_iprice_.get(position)
            Toast.makeText(this, "Position Clicked:" + " " + title_sub, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Link:" + " " + link_, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "lprice:" + " " + lprice, Toast.LENGTH_SHORT).show()



            Toast.makeText(this, "Position Clicked:" + " " + title_sub, Toast.LENGTH_SHORT).show()
            val detailintent = Intent(this, GraphActivity::class.java)
            detailintent.putExtra("title", title_sub);
            detailintent.putExtra("link", link_);
            detailintent.putExtra("lprice", lprice);
//
            startActivity(detailintent)
        }


        // add button click시 발생하는 event
        addButton.setOnClickListener {
            val intent = Intent(this, ItemSearchActivity::class.java)

            startActivity(intent)
        }



        @Override
        fun onBackPressed()
        {

            super.onBackPressed();
        }
//        copulation()
        fileIO.storeItemsToFile(storeArray)
    }

    fun split()
    {
        val array: Array<String>
        array = splitVariable.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        var q = 0
        for(i in array)
        {
            print(q)
            q++
            print(i+"\n")

        }
        var k = 0
        /*
        if(array.size >=2 )
        {
            for (i in array.indices)
            {
                if (array[i] == "title")
                {
                    data_array_items.add(k,array[i + 2])
                }
                if (array[i] == "link")
                {
                    data_array_link_.add(k,array[i + 2])
                }
                /*
                if (array[i] == "image")
                {
                    data_array_image_.add(k,array[i + 2])
                }
                */
                if (array[i] == "lprice")
                {
                    data_array_iprice_.add(k,array[i + 2])
                }
                if (array[i] == "hprice")
                {
                    data_array_hprice_.add(k,array[i + 2])
                }
                if (array[i] == "mallName")
                {
                    data_array_mallname_.add(k,array[i + 2])
                }
                if (array[i] == "productId")
                {
                    data_array_productid_.add(k,array[i + 2])
                    k++
                }

//            if (array[i] == "productType")
//            {
//                data_array_productType[k-1] = array[i + 2]
//
//            }

            }
        }*/

    }

    fun copulation()
    {
        for (i in data_array_items.indices)
        {
            storeArray.add(data_array_items[i]+","+data_array_link_[i]+","+data_array_iprice_[i]+","+data_array_productid_[i]+","+data_array_mallname_[i])//제목,링크,가격.제품ID,쇼핑몰 이름 순서로 합쳐서 선정
            //storeArray.add("title" + " & " + data_array_items[i] + "link" + " & " + data_array_link_ + "image" + " & " + "lprice" + " & " + "productId" + " & " + data_array_productid_ + "mallName" + " & " + data_array_mallname_)
        }


    }
}

