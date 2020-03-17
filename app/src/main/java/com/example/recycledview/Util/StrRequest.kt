package com.example.recycledview.Util

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest

fun getStringRequest(url: String) : StringRequest {

    val stringRequest = StringRequest(
        Request.Method.GET, url,Response.Listener<String> { response ->
            println("prov.. "+ response)
        },
        Response.ErrorListener {

            @Override
              fun onErrorResponse(error: VolleyError) {
                if (error.networkResponse != null) {
                    Log.d("Error Response code: ", "pa..."+error.networkResponse.statusCode)
                }
        }




        });

        return stringRequest

}