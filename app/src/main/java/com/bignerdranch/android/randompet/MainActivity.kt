package com.bignerdranch.android.randompet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
//    var petImageURL = ""

//    private lateinit var button: Button
//    private lateinit var petImage: ImageView

    private lateinit var petList: MutableList<String>
    private lateinit var rvPets: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("petImageURL", "pet image URL set")

        petList = mutableListOf()
        rvPets = findViewById(R.id.pet_list)

        getDogImageURL()

//        getNextImage(button, petImage)
    }

//    private fun getNextImage(button: Button, imageView: ImageView) {
//        button.setOnClickListener {
////            var choice = Random.nextInt(2)
////            if (choice == 0) {
////                getCatImageURL()
////            } else {
////                getDogImageURL()
////            }
//            getDogImageURL()
//
//            Glide.with(this)
//                . load(petImageURL)
//                .fitCenter()
//                .into(imageView)
//        }
//    }

    private fun getDogImageURL() {
        val client = AsyncHttpClient()

        client["https://dog.ceo/api/breeds/image/random/20", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog", "response successful$json")
                val petImageArray = json.jsonObject.getJSONArray("message")
                for (i in 0 until petImageArray.length()) {
                    petList.add(petImageArray.getString(i))
                }

                // Make we create the adapter only after the petList is created
                val adapter = PetAdapter(petList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPets.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }

//    private fun getCatImageURL() {
//        val client = AsyncHttpClient()
//
//        client["https://api.thecatapi.com/v1/images/search", object : JsonHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
//                Log.d("Dog", "response successful$json")
//                var resultsJSON = json.jsonArray.getJSONObject(0)
//                petImageURL = resultsJSON.getString("url")
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                errorResponse: String,
//                throwable: Throwable?
//            ) {
//                Log.d("Dog Error", errorResponse)
//            }
//        }]
//    }


}