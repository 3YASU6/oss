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
        val  db = FirebaseFirestore.getInstance()
              //  .document("items/detail")


        val name:String
        val titleitem:String
        val price:String
        val mall:String
        val name_item:String
        val i_price:String
        val mall_name:String
        val intent = intent
        val bd = intent.extras
        val ti = intent.getStringExtra("title")
//============set the text in the textview

        val swapVariable = intent.extras
        if (swapVariable != null) {
            val getName = swapVariable.get("title") as String
            tileText.setText(getName)
            val getiprice = swapVariable.get("iprice") as String
            lpriceText.setText(getiprice)
            val getmallname = swapVariable.get("mallname") as String
            mallNameText.setText(getmallname)
            val getimage = swapVariable.get("image") as String
            println("===================="+getimage)
            imageurl.loadUrl(getimage)


        }
        val calendar = Calendar.getInstance()
        val date = calendar.getTime()
        println(date)

        //to take the data into string
        var flavour = tileText.text.toString().trim()
        name_item = tileText.getText().toString()
        i_price = lpriceText.getText().toString()
        mall_name = mallNameText.getText().toString()

//        titleitem = trytext.getText().toString()

//        val itemss = HashMap<String, Any>()
//        if (flavour!=null) {
//            try {
//                // put the data in string database
//
//                itemss.put("name", "Samsung")
//                // insert the database to firebase
//                db.collection("itemName").document("detail").set(itemss).addOnSuccessListener {
//                    void: Void? -> Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()
//
//                }.addOnFailureListener {
//                    // if the data failure
//                    exception: java.lang.Exception -> Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
//                }
//            }catch (e:Exception) {
//                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
//            }
//        }else {
//            //if can't read the data
//            Toast.makeText(this, "Please fill up the fields :(", Toast.LENGTH_LONG).show()
//        }
        //========================================
        //==============add and insert to database

        val items = HashMap<String, Any>()
        val item = HashMap<String, Any>()
        if (flavour!=null) {
            try {
                // put the data in string database

                items.put("name", name_item)
                item.put("name", name_item)
                items.put("iprice", i_price)
                items.put("mall_name", mall_name)
                items.put("date", date)
                // insert the database to firebase

                db.collection("items").document(name_item).set(items).addOnSuccessListener {
                    void: Void? -> Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()

                }.addOnFailureListener {
                    // if the data failure
                    exception: java.lang.Exception -> Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                }
                db.collection("items").document("Title").update(item).addOnSuccessListener {
                    void: Void? -> Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()

                }.addOnFailureListener {
                    // if the data failure
                    exception: java.lang.Exception -> Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }else {
            //if can't read the data
            Toast.makeText(this, "Please fill up the fields :(", Toast.LENGTH_LONG).show()
        }



        // addToWishListButton click시 발생하는 event를 추가
        addToWishListButton.setOnClickListener {
            val intent = Intent(this, ItemListActivity::class.java)
            //=========prepare the text, so it can be taken in ItemList
            intent.putExtra("title", tileText.getText());
            intent.putExtra("iprice", lpriceText.getText());
            intent.putExtra("mallname",  mallNameText.getText());
            intent.putExtra("date",  date);

            startActivity(intent)
        }


    }
}
