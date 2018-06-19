package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.activity_show_more_item_info.*
import kotlinx.android.synthetic.main.webview.*
import retrofit2.http.Url
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import com.example.kimhy.open_source_project_naver_price_compare.R.layout.webview


class WebView : AppCompatActivity() {
    private val mWebView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)

        val swapVariable = intent.extras
        if (swapVariable != null) {
            val getlink = swapVariable.get("link") as String
            webvieww.loadUrl(getlink)
        }
    }
}
