package com.example.flixterp2ar

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val flix_person = "https://api.themoviedb.org/3/person/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val TAG = "MainActivity/"
class MainActivity : AppCompatActivity() {
    lateinit var flix: MutableList<Flix>
    lateinit var flixRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flix = mutableListOf()
        flixRecyclerView = findViewById(R.id.flixRV)
        val flixAdapter = flixAdapter(this,flix)
        flixRecyclerView.adapter = flixAdapter
        flixRecyclerView.layoutManager = GridLayoutManager(this , 2)

        val client = AsyncHttpClient()
        client.get(flix_person, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch flix: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched flix: $json")
                try {
                    val gson = Gson()

                    val resultsJSON: String = json.jsonObject.get("results").toString()

                    val arrayFlixType = object : TypeToken<List<Flix>>() {}.type
                    flix = gson.fromJson(resultsJSON, arrayFlixType)


                    flixRecyclerView.adapter =
                        flixAdapter(this@MainActivity, flix)

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })
    }
}