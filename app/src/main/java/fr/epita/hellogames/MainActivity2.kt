package fr.epita.hellogames

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity(), View.OnClickListener {


    var data =GameDetail()


    var callback = object : Callback<GameDetail> {
        override fun onFailure(call: Call<GameDetail>?, t: Throwable?) {
            // Code here what happens if calling the WebService fails
            Log.d("TAG", "WebService call failed")
        }
        override fun onResponse(call: Call<GameDetail>?,
                                response: Response<GameDetail>?) {
            // Code here what happens when WebService responds
            if (response != null) {
                if (response.code() == 200) {
                    // We got our data !
                    var responseData = response.body()
                    if (responseData != null) {
                        data = responseData
                        Log.d("TAG", "WebService success gamedetail: " + responseData)
                    }


                    // Changes texts here

                    Log.d("GAME", data.toString())
                    Name.text = "Name :" + data.name
                    nbPlayers.text = "Nb Players: " + data.players
                    Type.text = "Type: " + data.type
                    year.text = "Year : " + data.year.toString()
                    if (data.description_fr != null)
                        desciption.text = data.description_fr
                    else
                        desciption.text = data.description_en
                    imageView.setImageResource(imageFromid(data.name))
                    //companion object -> static object > MainActivity.myservice
                }
                else {

                    Log.d("TAG", "Reponse failed :" + response.code().toString())
                }
            }
        }
    }



    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.url -> {
                    val implicitIntent = Intent(ACTION_VIEW)
                    implicitIntent.data = Uri.parse(data.url)
                    startActivity(implicitIntent)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // retrieve the intent that caused the activity to open
        var originIntent = intent
// extract data from the intent
        var message2 = originIntent.getStringExtra("MESSAGE")

        Log.d("TAG", "MainActivity2 loaded, message : " + message2)

        myservice.service.getGameDetails(message2.toInt()).enqueue(callback)

        url.setOnClickListener(this@MainActivity2)
    }



}
