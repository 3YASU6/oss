package com.example.kimhy.open_source_project_naver_price_compare

import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
import retrofit2.http.Url

class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        //그래프작성
        // 입력 데이터
        val input = listOf(BarEntry(1f, 50f, "Green"), BarEntry(2f, 20f, "Yellow"), BarEntry(3f, 90f, "Red"), BarEntry(4f, 40f, "Blue"))
        val set = BarDataSet(input, "예의 설명문")
        set.colors = ColorTemplate.MATERIAL_COLORS.toList()
        set.isHighlightEnabled = true //タップで拡大
        val data = BarData(set)

        //데이터 서식
        data.setValueFormatter(PercentFormatter()) //フォーマット
        data.setValueTextSize(12f) //テキストサイズ
        data.setValueTextColor(Color.WHITE) //色
        data.isHighlightEnabled = true
        bar_chart.data = data

        //그래프의 UI설정
        bar_chart.legend.isEnabled = true //凡例の表示

        // X측
        val xAxis = bar_chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // Y측(우)
        val rAxis = bar_chart.axisRight
        rAxis.isEnabled = false
        rAxis.mAxisMaximum = 80f
        rAxis.mAxisMinimum = 10f

        // Y측(좌)
        val lAxis = bar_chart.axisLeft
        lAxis.textColor = Color.MAGENTA

        //설명문
        val description = Description()
        description.text = "그래프의 설명문"
        bar_chart.description = description
        bar_chart.invalidate()

        // 애니메이션
        bar_chart.animateY(2000, Easing.EasingOption.EaseInBounce)



        //buyButton click시 발생하는 event
        buyButton.setOnClickListener{
            // 해당 상품에 이동
            //Naver_API.link
            Uri.parse("https://developer.android.com/reference/android/net/Uri")

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
