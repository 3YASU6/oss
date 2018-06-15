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

    lateinit var db: DocumentReference

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
        val ItemName = intent.getStringExtra( "title" )
        val ItemLink = intent.getStringExtra( "link" )

        val ItemLowprice = intent.getStringExtra( "iprice" )
        val ItemMallname = intent.getStringExtra( "mallname" )
        val ItemDate = intent.getStringExtra( "date" )
        val ItemProductId = intent.getStringExtra("productid")

        var fileIO = FileIO(this)
        var data_array_items = fileIO.loadItemsFromFile()
        var data_array_iprice_  = fileIO.loadItemsFromFile()
        var data_array_mallname_ = fileIO.loadItemsFromFile()
        var data_array_link_ = fileIO.loadItemsFromFile()
        var data_array_image_ = fileIO.loadItemsFromFile()
        var data_array_date_ = fileIO.loadItemsFromFile()
        var data_array_hprice_ = fileIO.loadItemsFromFile()
        var data_array_productid_ = fileIO.loadItemsFromFile()

        val swapVariable = intent.extras
        if (swapVariable != null) {
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
        val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        thisView.adapter = itemsAdapter




        // item click시 발생하는 event
        thisView.setOnItemClickListener { parent, view, position, id ->
            // activity_graph 화면에 이동
            adapter.getItem(position)
            val position_ :Int = position
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
            detailintent.putExtra("iprice", lprice);
//
            startActivity(detailintent)
        }


        // add button click시 발생하는 event
        addButton.setOnClickListener {
            val intent = Intent(this, ItemSearchActivity::class.java)

            startActivity(intent)
        }



        @Override fun onBackPressed()
        {

            super.onBackPressed();
        }
        fileIO.storeItemsToFile(data_array_items)
    }
}

class Note
{

    var id: String? = null
    var title: String? = null
    var content: String? = null

    constructor()
    {
    }

    constructor(id: String, title: String, content: String)
    {
        this.id = id
        this.title = title
        this.content = content
    }

    constructor(title: String, content: String)
    {
        this.title = title
        this.content = content
    }

    fun toMap(): Map<String, Any>
    {

        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("content", content!!)

        return result
    }
}