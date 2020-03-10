package com.example.recycledview.Util

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

fun getStringRequest() : StringRequest {
    val url = "https://randomuser.me/api/?results=20"

    val stringRequest = StringRequest(
        Request.Method.GET, url,
        Response.Listener<String> { response ->
            println(response)
        },
        Response.ErrorListener {
            println("error")
        }
    )

    return stringRequest
}
