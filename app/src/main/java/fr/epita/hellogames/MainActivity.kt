package fr.epita.hellogames

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

        var message = "5"
        var explicitIntent = Intent(this, MainActivity2::class.java)


        if (v != null) {
            when (v.id) {
                R.id.image1 -> {
                    message = data[0].id.toString()
                }
                R.id.image2 -> {
                    message = data[1].id.toString()
                }
                R.id.image3 -> {
                    message = data[2].id.toString()
                }
                R.id.image4 -> {
                    message = data[3].id.toString()
                }
            }

            explicitIntent.putExtra("MESSAGE", message)

            startActivity(explicitIntent)

        }
        // Create an explicit intent

    }


    // A List to store or objects
    var data = arrayListOf<GameObject>()
    // The base URL where the WebService is located



    var callback = object : Callback<List<GameObject>> {
        override fun onFailure(call: Call<List<GameObject>>?, t: Throwable?) {
            // Code here what happens if calling the WebService fails
            Log.d("TAG", "WebService call failed")
        }
        override fun onResponse(call: Call<List<GameObject>>?,
                                response: Response<List<GameObject>>?) {
            // Code here what happens when WebService responds
            if (response != null) {
                if (response.code() == 200) {
                    // We got our data !
                    var responseData = response.body()
                    if (responseData != null) {
                        data.addAll(responseData)
                        Log.d("TAG", "WebService success game list: " + data.size)

                        // Changes images here

                        // Choix random

                        data.shuffle()

                        image1.setImageResource(imageFromid(data[0].name))
                        image2.setImageResource(imageFromid(data[1].name))
                        image3.setImageResource(imageFromid(data[2].name))
                        image4.setImageResource(imageFromid(data[3].name))
                    }
                }
            }


            //companion object -> static object > MainActivity.myservice
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        image1.setOnClickListener(this@MainActivity)
        image2.setOnClickListener(this@MainActivity)
        image3.setOnClickListener(this@MainActivity)
        image4.setOnClickListener(this@MainActivity)

        myservice.service.getGameList().enqueue(callback)


    }
}


