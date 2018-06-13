package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.icu.util.ULocale
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
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
import com.google.android.gms.tasks.Task


class ItemListActivity : AppCompatActivity() {

    lateinit var db: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        // db에 DB의 Instance 취득
        val  db = FirebaseFirestore.getInstance().document("items/detail")

        val count:Int
        val idnum:Int
        val name:String
        val price:String
        val mall:String
        val aa:String
        val i_price:String
        val mall_name:String
        val intent = intent
        val bd = intent.extras
        val ti = intent.getStringExtra("title")

        // 화면에 표시 되는 상품 수
        val display = 20

        // ListView 를 취득
        val thisView = findViewById(R.id.listView) as ListView

        // ListView의 Item 에 놓는 데이터 배열를 초기화
        var data_array_items = Array(display, { i -> "Title-$i" })
        var data_array_iprice = Array(display, { i -> "Price-$i" })

        //set the text in the textview
        if (bd != null) {
            //  get data from database
            db.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                if (task.isSuccessful) {
                    //FireBase에서 전체 데이터를 취득
                    var ALL_DB_Data = task.result.data.toString()

                    ALL_DB_Data = ""

                    if (ALL_DB_Data.isNullOrEmpty()) { // ALL_DB_Data가 null or 비어있을때

                        Toast.makeText(this, "no such a data", Toast.LENGTH_LONG).show()

                    } else {
                        // ALL_DB_Data 내용 : name*****=*****,mall_name1=***,iprice1=****
                        // name_temp 내용 : name***** ↓첫번째 = 이전 문자를 취득
                        val name_temp = ALL_DB_Data.substringBefore('=')
                        // Item_name 내용 : ***** ↓앞에서 5번째 문자 이후의 문자열을 취득
                        val item_name = name_temp.substring(5)

                        // Item_price 내용 : **** ↓마지막 = 이후 문자를 취득
                        val Item_price = ALL_DB_Data.substringAfter('=')




                        Toast.makeText(this,item_name, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "get failed with" + task.exception, Toast.LENGTH_LONG).show()
                }
            })

        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        thisView.adapter = adapter

//        val database = FirebaseDatabase.getInstance()
//        val ref = database.getReference("server/saving-data/fireblog")
//        val myRef = database.getReference("message")




            // item click시 발생하는 event
            thisView.setOnItemClickListener { _, view, _, _ ->
                // activity_graph 화면에 이동
                val intent = Intent(this, GraphActivity::class.java)
                startActivity(intent)
            }


            // add button click시 발생하는 event
            addButton.setOnClickListener {
                val intent = Intent(this, ItemSearchActivity::class.java)
                startActivity(intent)
            }


        }
    }

