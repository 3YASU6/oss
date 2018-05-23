package com.example.kimhy.open_source_project_naver_price_compare

import android.app.LoaderManager
import android.database.Cursor
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface  Api {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String)
            : Call<NewsResponse>;
}

class NewsResponse(val data: DataResponse)

class DataResponse(
        val children: List<ChildrenResponse>,
        val after: String?,
        val before: String?
)

class ChildrenResponse(val data: NewsDataResponse)

class NewsDataResponse(
        val itemname: String,
        val price_now: Int,
        val priec_lat: Long,
        val url: String
)
//private var item: RecyclerView? = null
//
//override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//    val view = inflater.inflate(R.layout.news_fragment, container, false)
//    newsList = view.findViewById(R.id.news_list) as RecyclerView?
//    newsList?.setHasFixedSize(true) // use this setting to improve performance
//    newsList?.layoutManager = LinearLayoutManager(context)
//
//    return view
//}
//inner class RecyclerView internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {
//
//}

//class item : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
//    /**
//     * Keep track of the login task to ensure we can cancel it if requested.
//     */
//    private var mAuthTask:
//
//            overide
//
//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//    }
//}
