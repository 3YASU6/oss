package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
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






class ItemListActivity : AppCompatActivity() {

    lateinit var db: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        db = FirebaseFirestore.getInstance().document("items/detail")
        val thisView = findViewById(R.id.listView) as ListView
        val data_array_items = Array(20, { i -> "Title-$i" })
        val listvieww = Array(20, {  })


        val aa:String
        val i_price:String
        val mall_name:String
        val intent = intent
        val bd = intent.extras
        val ti = intent.getStringExtra("title")


        //===============================================
//set the text in the textview
        if (bd != null) {
            val getName = bd.get("title") as String?
            trytext.setText(getName)
            val get_iprice = bd.get("iprice") as String?
            trytextprice.setText(get_iprice)
            val get_imall = bd.get("mallname") as String?
            trytextmall.setText(get_imall)


            var flavour = trytext.text.toString().trim()
            aa = trytext.getText().toString()
            i_price = trytextprice.getText().toString()
            mall_name = trytextmall.getText().toString()


           //========================================
 //==============add and insert to database
            if (flavour!=null) {
                try {
                    val items = HashMap<String, Any>()
           //         items.put("detail", trytext)
                    db.collection(aa).document("detail").collection(i_price).document("detailprice").set(items).addOnSuccessListener {
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



//=======================================================================================================================================
            //==================get data from database (ERROR)
            val docReff = db.collection("items").document("detail")
            docReff.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {

                        Log.d("TAG", "before")
                        //try to print data
                        for ( i in listvieww.indices) {
                            println(task.result)
                            Toast.makeText(this, "DocumentSnapshot data: " + listvieww[i]
                                    , Toast.LENGTH_LONG).show()
                        }

                        Log.d("TAG", "message")

                    } else {
                        Toast.makeText(this, "no such a data", Toast.LENGTH_LONG).show()

                    }
                } else {
                    Toast.makeText(this, "get failed with" + task.exception, Toast.LENGTH_LONG).show()
                }
            })

        //=================== custom object
            val docRef = db.collection("items").document("detail")
            docRef.get().addOnSuccessListener(OnSuccessListener<DocumentSnapshot> { documentSnapshot ->
                val note = documentSnapshot.toObject(ItemListActivity::class.java)
            })

//================================================================================================================







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

