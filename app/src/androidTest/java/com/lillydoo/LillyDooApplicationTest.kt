package com.lillydoo

class LillyDooApplicationTest : LillyDooApplication() {

    var url = "http://127.0.0.1:8080"

    fun getBaseUrl(): String {
        return url
    }
}