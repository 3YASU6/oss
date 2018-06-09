package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_list.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_show_more_item_info.*
import org.json.JSONException
import kotlin.coroutines.experimental.EmptyCoroutineContext


class ItemListActivity : AppCompatActivity() {

    lateinit var db: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        db = FirebaseFirestore.getInstance().document("items/detail")
        val thisView = findViewById(R.id.listView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })

        val aa:String
        val i_price:String
        val mall_name:String
        val intent = intent
        val bd = intent.extras
        val ti = intent.getStringExtra("title")
        val listview = ArrayList<String>()

        //===============================================
//set the text in the textview
        if (bd != null) {
            val getName = bd.get("title") as String?
            trytext.setText(getName)
//            val getiprice = bd.get("iprice") as String
//            lpriceText.setText(getiprice)
//            val getmallname = bd.get("mallname") as String
//            mallNameText.setText(getmallname)

            var flavour = trytext.text.toString().trim()
            aa = trytext.getText().toString()
            i_price = trytext.getText().toString()
            mall_name = trytext.getText().toString()

           //========================================
 //add to database
            if (flavour!=null) {
                try {
                    val items = HashMap<String, Any>()
           //         items.put("detail", trytext)
                    db.collection(aa).document("detail").set(items).addOnSuccessListener {
                        void: Void? -> Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        exception: java.lang.Exception -> Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }catch (e:Exception) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
                }
            }else {
                Toast.makeText(this, "Please fill up the fields :(", Toast.LENGTH_LONG).show()
            }













            }
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_array_items)
            thisView.adapter = adapter
            val database = FirebaseDatabase.getInstance()
            val ref = database.getReference("server/saving-data/fireblog")
            val myRef = database.getReference("message")
//

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

