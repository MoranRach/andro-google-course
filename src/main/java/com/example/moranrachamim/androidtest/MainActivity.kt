package com.example.moranrachamim.androidtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.time.DayOfWeek

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var orders: List<OrdersAnalyzer.Order> = OrdersAnalyzer().getJsonListFromFile(this)
        var map: Map<DayOfWeek, Int>? = OrdersAnalyzer().totalDailySales(orders)
    }
}
