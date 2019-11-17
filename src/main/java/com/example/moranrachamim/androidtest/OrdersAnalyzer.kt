package com.example.moranrachamim.androidtest

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class OrdersAnalyzer {

    data class Order(val orderId: Int, val creationDate: String, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    //data class DayOfWeek()

    //data class Orders(val orders: List<Order>)

    fun getJsonListFromFile (context: Context): List<Order> {

        val gson: Gson = Gson() //GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val bufferedReader: BufferedReader = context.assets.open( "orders.json" ).bufferedReader()
        var inputString: String = bufferedReader.use { it.readText() }
        //inputString = gson.toJson(inputString)
        //var order = gson.fromJson(inputString, Order::class.java)

        return gson.fromJson(inputString, Array<Order>::class.java).toList()
    }

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int>? {

        //var mapOfWeekly: Map<DayOfWeek, Int>? = mutableMapOf()
        var mapOfWeekly = mutableMapOf<DayOfWeek, Int>()

        for (order in orders)   {
            /*calendar.set(Calendar.YEAR, date.year)
            calendar.set(Calendar.MONTH, date.month.value)
            calendar.set(Calendar.DAY_OF_MONTH, date.dayOfMonth)*/
            /*calendar.set(Calendar.YEAR, 2017)
            calendar.set(Calendar.MONTH, 7)
            calendar.set(Calendar.DAY_OF_MONTH, 21)*/
            var count : Int = 0
            for (orderLine in order.orderLines) {
                 count += orderLine.quantity
            }
            if (count > 0)  {
                var date = LocalDateTime.parse(order.creationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                val calendar: Calendar = Calendar.getInstance()
                //calendar.set(date.year, date.month.value, date.dayOfMonth)
                calendar.set(Calendar.YEAR, date.year)
                calendar.set(Calendar.DAY_OF_YEAR, date.dayOfYear)
                //mapOfWeekly?.plus(DayOfWeek.values()[calendar.get(Calendar.DAY_OF_WEEK)] to count)//
                mapOfWeekly.plusAssign(mutableMapOf(DayOfWeek.values()[calendar.get(getDayOfWeek(Calendar.DAY_OF_WEEK))] to count))
            }
        }


        return mapOfWeekly
    }

    fun getDayOfWeek(dayOfWeek: Int): Int {

        when (dayOfWeek)    {
            1 -> return 6
            2 -> return 0
            3 -> return 1
            4 -> return 2
            5 -> return 3
            6 -> return 4
            7 -> return 5
        }

        return 0
    }

}