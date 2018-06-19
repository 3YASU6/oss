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
    ////////////////////////////// 변수 정의. フィールド定義  ///////////////////////////////
    // ※lateinit : 변수의 초기와를 느린다.
    lateinit var data_array_items: ArrayList<String>
    lateinit var data_array_iprice_: ArrayList<String>
    lateinit var data_array_mallname_: ArrayList<String>
    lateinit var data_array_link_: ArrayList<String>
    lateinit var data_array_image_: ArrayList<String>
    lateinit var data_array_date_: ArrayList<String>
    lateinit var data_array_hprice_: ArrayList<String>
    lateinit var data_array_productid_: ArrayList<String>

    lateinit var fileArray: ArrayList<String>

    // File 에 저장하는 데이터
    lateinit var storeArray: ArrayList<String>



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        // ↓변수 초기화. フィールド初期化
        data_array_items = ArrayList<String>()
        data_array_iprice_ = ArrayList<String>()
        data_array_mallname_ = ArrayList<String>()
        data_array_link_ = ArrayList<String>()
        data_array_image_ = ArrayList<String>()
        data_array_date_ = ArrayList<String>()
        data_array_hprice_ = ArrayList<String>()
        data_array_productid_ = ArrayList<String>()
        storeArray = ArrayList<String>()



        //////////////////////////// 파일 읽기. ファイル読み込み /////////////////////////////////
        var fileIO = FileIO(this) // File의 인스턴스 생성. fileのインスタンス生成
        fileArray = fileIO.loadItemsFromFile() // File에서 내용 읽어오기. fileから内容(ArrayList)を読み取ってくる  1行：1商品

        // 전상품수의 loop.全商品分のループ
        for(i in fileArray.indices){
            val array_splied_itemInfo = split(fileArray[i])  //상품 정보를 split한 결과
            data_array_items.add(array_splied_itemInfo[0])
            data_array_link_.add(array_splied_itemInfo[1])
            data_array_iprice_.add(array_splied_itemInfo[2])
            data_array_productid_.add(array_splied_itemInfo[3])
            data_array_mallname_.add(array_splied_itemInfo[4])
        }



        /////////////////////////////////  itemList표시 ///////////////////////////////////
        // ListView 를 취득
        val thisView = findViewById(R.id.listView) as ListView

        // 애덥터에 데이터 배열을 넣는다
        val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
        thisView.adapter = itemsAdapter


        // item click시 발생하는 event
        thisView.setOnItemClickListener { parent, view, position, id ->
            itemsAdapter.getItem(position)
            data_array_items.get(position)
            data_array_link_.get(position)
            data_array_iprice_.get(position)

            // activity_graph 화면에 이동
            val graphIntent = Intent(this, GraphActivity::class.java)
            graphIntent.putExtra("title", data_array_items.get(position))
            graphIntent.putExtra("link", data_array_link_.get(position))
            graphIntent.putExtra("lprice", data_array_iprice_.get(position))
            graphIntent.putExtra("productid", data_array_productid_.get(position))
            startActivity(graphIntent)
        }

        // add button 을 click 하면 ItemSearch 화면으로 이동
        addButton.setOnClickListener {
            val searchIntent = Intent(this, ItemSearchActivity::class.java)
            startActivity(searchIntent)
        }
    }


    fun split(splitVariable : String) : Array<String>
    {
        val splitedArray: Array<String> = splitVariable.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()   // 특히 의미없음 : .toRegex()).dropLastWhile({ it.isEmpty() })
        return splitedArray
    }
}

