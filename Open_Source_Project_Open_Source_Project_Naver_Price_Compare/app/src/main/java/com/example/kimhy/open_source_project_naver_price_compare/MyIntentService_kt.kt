package com.example.kimhy.open_source_project_naver_price_compare

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.widget.Toast

// TODO: アクションの名前を変更し、これを実行するタスクを記述するアクション名を選択します
private const val ACTION_GetItemInfoFromNaver = "com.example.kimhy.open_source_project_naver_price_compare.action.GetItemInfoFromNaver"

// TODO: パラメータの名前を変更する
private const val EXTRA_PARAM_ITEM_NAME = "com.example.kimhy.open_source_project_naver_price_compare.extra.PARAM_ITEM_NAME"

/**
 * 別のハンドラスレッド上のサービスで非同期タスク要求を処理するための[IntentService]サブクラス。
 * TODO：クラスのインテントアクション、追加のパラメーター、およびスタティックヘルパーメソッドをカスタマイズします。
 */
class MyIntentService_kt : IntentService("MyIntentService_kt") {

    // TODO: 제공된 매개 변수를 사용하여 제공된 백그라운드 스레드에서 GetItemInfoFromNaver 액션을 처리.
    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_GetItemInfoFromNaver -> {
                val itemName = intent.getStringExtra("Title")
                val itemID = intent.getStringExtra("productID")
                //val param1 = intent.getStringExtra(EXTRA_PARAM_ITEM_NAME)
                handleAction_GetItemInfoFromNaver(itemName,itemID)
            }
        }
    }

    /**
     * 提供されたバックグラウンドスレッドのアクションGetItemInfoFromNaverを指定されたパラメータで処理します。
     */
    private fun handleAction_GetItemInfoFromNaver(itemName: String, itemID: String) {

        Toast.makeText(this, "MyIntentService !!!!!"+itemName+" "+ itemID, Toast.LENGTH_LONG).show()


        //thread 만들어서 불러옴 http://plaboratory.org/archives/108 참조
//        val thread = Naver_API(itemName)
//        thread.start()
//
//        try {
//            thread.join()// API 요청 후 데이터를 다 가져올때까지 대기 반드시 사용할 것
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        var result = thread.getResult()
//        //println("ItemSearch " + result)
//        //쓰레드 처리 끝
//        var title: Array<String>? = null
//        title = thread.getTitle()
//
//        var iprice: Array<String>? = null
//        iprice = thread.getIprice()
//
//        var mallname: Array<String>? = null
//        mallname = thread.getMallName()
//
//        var link: Array<String>? = null
//        link = thread.getLink()
//
//        var image: Array<String>? = null
//        image = thread.getImage()

    }


    companion object {
        /**
         * このサービスを開始して、指定されたパラメータでアクションGetItemInfoFromNaverを実行します。
         * サービスが既にタスクを実行している場合、このアクションはキューに入れられます。
         * @see IntentService
         */
        // TODO: ヘルパーメソッドのカスタマイズ
        @JvmStatic
        fun startActionGetItemInfoFromNaver(context: Context, param1: String) {
            val intent = Intent(context, MyIntentService_kt::class.java).apply {
                action = ACTION_GetItemInfoFromNaver
                putExtra(EXTRA_PARAM_ITEM_NAME, param1)
            }
            context.startService(intent)
        }
    }
}
