package com.example.kimhy.open_source_project_naver_price_compare

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis


import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

import kotlinx.android.synthetic.main.activity_graph.*
import retrofit2.http.Url
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        // ItemList화면에서 오는 intent값을 text에 표시
        val priceText = findViewById(R.id.priceText) as TextView
        val titleText = findViewById(R.id.titleText) as TextView
        priceText.setText(intent.getStringExtra("lprice"))
        titleText.setText(intent.getStringExtra("title"))

        // ItemList화면에서 오는 intent값을 취득
        val link = intent.getStringExtra("link")
        val productID = intent.getStringExtra("productid")



        //////////////////////////////// chart 정의  ////////////////////////////////////////
        val mChart = findViewById(R.id.chart) as LineChart

        // 터치 제스처 사용
        mChart.setTouchEnabled(true)

        // 크기 조정 및 드래그 사용
        mChart.setDragEnabled(true)
        mChart.setScaleEnabled(true)
        mChart.setDrawGridBackground(false)

        // 비활성화 된 경우 배율을 x 축과 y 축에서 개별적으로 수행 할 수 있습니다.
        mChart.setPinchZoom(true)

        // 배경색을 설정
        mChart.setBackgroundColor(Color.WHITE)

        // TODO: FireBase에서 끌고 온 상품 가격변동 정보들을 저장 (가격 = price)  ※지금은 더미
        val price_data_array = arrayOf(900f, 1000f, 1200f, 1200f, 800f, 750f) //intent.getStringExtra("lprice")
        // TODO: FireBase에서 끌고 온 상품 가격변동 정보들을 저장 (날짜 = date)  ※지금은 더미
        val date_data_array = arrayOf(2f, 3f, 4f, 5f, 6f, 7f) // intent.getStringExtra("date")

        // 그래프의 Point가 되는 데이터 배열
        val xy_data = ArrayList<Entry>()

        for(i in date_data_array.indices) {
            xy_data.add(Entry(date_data_array[i], price_data_array[i])) // xy값(=Entry)를 xy_data에 설정 (X = date_data, Y = price_data)
        }

        // xy_data를 LineDataSet인 set에 저장
        val set = LineDataSet(xy_data, "Price_History")

        //LineDataSet를 생성하고 set를 놓기
        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(set)

        //  LineData인 data에 dataSet 놓기
        val data = LineData(dataSet)
        data.setValueTextColor(Color.BLACK)

        // 그래프에 데이터를 추가
        mChart.setData(data)

        //  라인의 rabel를 설정
        val l = mChart.getLegend()
        l.setForm(Legend.LegendForm.LINE)
        l.setTextColor(Color.BLACK)

        val xl = mChart.getXAxis()
        xl.setLabelCount(9)
        xl.setPosition(XAxis.XAxisPosition.BOTTOM)
        xl.setTextColor(Color.BLACK)

        val leftAxis = mChart.getAxisLeft()
        leftAxis.setTextColor(Color.BLACK)
        leftAxis.setDrawGridLines(true)

        val rightAxis = mChart.getAxisRight()
        rightAxis.setEnabled(false)


        //buyButton click시 해당 상품 구매 사이트에 이동
        buyButton.setOnClickListener {
            val WebViewIntent = Intent(this, WebView::class.java)
            WebViewIntent.putExtra("link", link)
            startActivity(WebViewIntent)
        }


        //delete button click시 발생하는 event
        deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(getString(R.string.messageOfDeleteDialog))
            builder.setTitle(getString(R.string.titleOfDeleteDialog))
            builder.setPositiveButton("YES") { dialog, which ->
                // TODO: YES를 눌렀을 때 ItemList에서 Item삭제 : items.txt에서 해당 상품 정보 삭제

                //////////////////////////// 파일 읽기. ファイル読み込み /////////////////////////////////
                var fileArray: ArrayList<String>
                var fileIO = FileIO(this) // File의 인스턴스 생성. Fileのインスタンス生成
                fileArray = fileIO.loadItemsFromFile() // File에서 부터 내용 읽고 오기 . Fileから内容(ArrayList)を読み取ってくる  1行：1商品
                var newFileArray = ArrayList<String>()

                // 전상품의 loop. 全商品分のループ
                for(i in fileArray.indices){
                    val array_splied_itemInfo = split(fileArray[i])  //상품 정보를 split한 결과. アイテムの情報をsplitした結果
                    if(array_splied_itemInfo[3] != productID) // if same productID
                    {
                        newFileArray.add(fileArray[i])
                    }
                }

                val fileio = FileIO(this)
                fileio.storeItemsToFile(newFileArray)

                // . Toast로 삭제 완료됐음을 표시
                Toast.makeText(this, getString(R.string.deleteFinishedMessage), Toast.LENGTH_SHORT).show()

                // ItemList 에 화면 이동
                val goToItemListIntent = Intent(this, ItemListActivity::class.java)
                startActivity(goToItemListIntent)
            }
            builder.setNeutralButton("Cancel") { _, _ ->
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }

    fun split(splitVariable : String) : Array<String>
    {
        val splitedArray: Array<String> = splitVariable.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()   // 특히 의미없음 : .toRegex()).dropLastWhile({ it.isEmpty() })
        return splitedArray
    }

}
