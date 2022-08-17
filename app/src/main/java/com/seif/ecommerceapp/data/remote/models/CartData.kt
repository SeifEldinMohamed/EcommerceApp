package com.seif.ecommerceapp.data.remote.models

class CartData (url:String,name:String,num_of_orders:Int,price:Double){
    var url = url
    var name = name
    var num_of_orders = num_of_orders
    var price = price

    fun totalprice():Double{
        return price * num_of_orders
    }

}