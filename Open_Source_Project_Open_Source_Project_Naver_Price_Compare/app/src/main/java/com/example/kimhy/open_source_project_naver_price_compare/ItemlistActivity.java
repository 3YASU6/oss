package com.example.kimhy.open_source_project_naver_price_compare;

import android.app.Activity;
import android.os.Bundle;


public class ItemlistActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist);
    }
}





/*
package com.example.kimhy.open_source_project_naver_price_compare

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast

class ItemlistActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemlist)
        // Set up the itemlist form.


        // 화면 전이 확인 용 다이어로그
        val builder = AlertDialog.Builder(this@ItemlistActivity)
        // Set the alert dialog title
        builder.setTitle("Itemlist")
        // Display a message on alert dialog
        builder.setMessage("This is Itemlist")
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialog, which ->
            // Do something when user press the positive button
            Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()

        }
    }
}

*/