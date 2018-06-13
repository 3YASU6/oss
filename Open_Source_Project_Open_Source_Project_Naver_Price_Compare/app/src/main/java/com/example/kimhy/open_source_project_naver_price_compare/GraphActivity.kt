package com.example.kimhy.open_source_project_naver_price_compare

import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.ImageButton
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
import kotlinx.android.synthetic.main.activity_show_more_item_info.*


class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

 //       val l_price:String
        val mChart = findViewById(R.id.chart) as LineChart

        val l_price : String

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

        val swapVariable = intent.extras
        if (swapVariable != null) {
//            val getName = swapVariable.get("title") as String
//            titleText.setText(getName)
//            val getiprice = swapVariable.get("iprice") as String
//            priceText.setText(getiprice)
//            val getmallname = swapVariable.get("mallname") as String
//            mallNameText.setText(getmallname)
//            val getimage = swapVariable.get("image") as String
//            println("====================" + getimage)
//            imageurl.loadUrl(getimage)

            // l_price = lpriceText.getText().toString()
            // TODO: FireBase에서 끌고 온 상품 가격변동 정보들을 저장 (가격 = price)  ※지금은 더미
            val price_from_fireBase = arrayOf(900f, 1000f, 1200f, 1200f, 800f, 750f)
            // TODO: FireBase에서 끌고 온 상품 가격변동 정보들을 저장 (날짜 = date)  ※지금은 더미
            val date_from_fireBase = arrayOf(2f, 3f, 4f, 5f, 6f, 7f)

            // 그래프의 Point가 되는 데이터 배열
            val xy_data = ArrayList<Entry>()

            // FireBase에서 끌고 온 Date, Price를 그래프 Point(x값, y값)로 설정
            for (i in date_from_fireBase.indices) {

                xy_data.add(Entry(date_from_fireBase[i], price_from_fireBase[i])) // xy값(=Entry)를 xy_data에 설정 (X = date_data, Y = price_data)

            //    xy_data.add(Entry(date_from_fireBase[i], price_from_fireBase[i])) // xy값(=Entry)를 xy_data에 설정 (X = date_data, Y = price_data)

      //          xy_data.add(Entry(date_from_fireBase[i], price_from_fireBase[i])) // xy값(=Entry)를 xy_data에 설정 (X = date_data, Y = price_data)

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


            val buyButton = findViewById(R.id.buyButton) as Button
            //buyButton click시 발생하는 event
            buyButton.setOnClickListener {
                // 해당 상품 구매 사이트에 이동
                Toast.makeText(this, "해당 상품 구매 사이트에 이동", Toast.LENGTH_SHORT).show()
            }

            val deleteButton = findViewById(R.id.deleteButton) as ImageButton
            //delete button click시 발생하는 event
            deleteButton.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(getString(R.string.messageOfDeleteDialog))
                builder.setTitle(getString(R.string.titleOfDeleteDialog))
                builder.setPositiveButton("YES") { dialog, which ->
                    // YES를 눌렀을 때 발생돼는 event를 여기에 기술
                    // Item을 DB에서 삭제


                    // Toast로 삭제 완료됐음을 표시
                    Toast.makeText(this, getString(R.string.deleteFinishedMessage), Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Cancel") { _, _ ->
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

            }
        }
    }
}
