package com.example.kimhy.open_source_project_naver_price_compare

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

import kotlinx.android.synthetic.main.activity_graph.*

class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        //棒グラフを作成する
        // インプットデータ
        val input = listOf(BarEntry(1f, 50f, "Green"), BarEntry(2f, 20f, "Yellow"), BarEntry(3f, 90f, "Red"), BarEntry(4f, 40f, "Blue"))
        val set = BarDataSet(input, "凡例の説明文")
        set.colors = ColorTemplate.MATERIAL_COLORS.toList()
        set.isHighlightEnabled = true //タップで拡大
        val data = BarData(set)

        //データの書式
        data.setValueFormatter(PercentFormatter()) //フォーマット
        data.setValueTextSize(12f) //テキストサイズ
        data.setValueTextColor(Color.WHITE) //色
        data.isHighlightEnabled = true
        bar_chart.data = data

        //グラフのUI設定
        bar_chart.legend.isEnabled = true //凡例の表示

        //横軸
        val xAxis = bar_chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        //縦軸(右)
        val rAxis = bar_chart.axisRight
        rAxis.isEnabled = false
        rAxis.mAxisMaximum = 80f
        rAxis.mAxisMinimum = 10f

        //縦軸(左)
        val lAxis = bar_chart.axisLeft
        lAxis.textColor = Color.MAGENTA

        //説明文
        val description = Description()
        description.text = "グラフの説明文"
        bar_chart.description = description
        bar_chart.invalidate()

        // アニメーション
        bar_chart.animateY(2000, Easing.EasingOption.EaseInBounce)



        buyButton.setOnClickListener{
            // Toast 표시
            val message = "link to shopping site"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        deleteButton.setOnClickListener {
            //Toast 표시
            val message = "delete this item information"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
