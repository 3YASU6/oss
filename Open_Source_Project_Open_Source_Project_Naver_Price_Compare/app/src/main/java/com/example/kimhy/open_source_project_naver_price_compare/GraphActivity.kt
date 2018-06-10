package com.example.kimhy.open_source_project_naver_price_compare

import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ListView
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
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineRadarDataSet


class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        val mChart = findViewById(R.id.chart) as LineChart

        // 터치 제스처 사용
        mChart.setTouchEnabled(true)

        // 크기 조정 및 드래그 사용
        mChart.setDragEnabled(true)
        mChart.setScaleEnabled(true)
        mChart.setDrawGridBackground(false)

        // 비활성화 된 경우 배율을 x 축과 y 축에서 개별적으로 수행 할 수 있습니다.
        mChart.setPinchZoom(true)

        // 대체 배경색을 설정
        mChart.setBackgroundColor(Color.LTGRAY)

        val data = LineData()
        data.setValueTextColor(Color.BLACK)

        // 데이터 추가
        mChart.setData(data)

        //  라인의 rabel를 설정
        val l = mChart.getLegend()
        l.setForm(Legend.LegendForm.LINE)
        l.setTextColor(Color.BLACK)

        val xl = mChart.getXAxis()
        xl.setTextColor(Color.BLACK)
        //xl.setLabelsToSkip(9)

        val leftAxis = mChart.getAxisLeft()
        leftAxis.setTextColor(Color.BLACK)
        leftAxis.setAxisMaxValue(3.0f)
        leftAxis.setAxisMinValue(-3.0f)
        leftAxis.setStartAtZero(false)
        leftAxis.setDrawGridLines(true)

        val rightAxis = mChart.getAxisRight()
        rightAxis.setEnabled(false)


        //buyButton click시 발생하는 event
        buyButton.setOnClickListener{
            // 해당 상품에 이동
            //Naver_API.link
          //  Uri.parse("https://developer.android.com/reference/android/net/Uri")

        }

        //delete button click시 발생하는 event
        deleteButton.setOnClickListener {
            val builder =  AlertDialog.Builder(this)
            builder.setMessage(getString(R.string.messageOfDeleteDialog))
            builder.setTitle(getString(R.string.titleOfDeleteDialog))
            builder.setPositiveButton("YES"){dialog, which ->
                // YES를 눌렀을 때 발생돼는 event를 여기에 기술
                // Item을 DB에서 삭제









                // Toast로 삭제 완료됐음을 표시
                Toast.makeText(applicationContext, getString(R.string.deleteFinishedMessage), Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Cancel"){_,_ ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }
}
