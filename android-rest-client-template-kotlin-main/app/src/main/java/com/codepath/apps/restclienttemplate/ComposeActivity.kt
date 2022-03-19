package com.codepath.apps.restclienttemplate

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codepath.apps.restclienttemplate.models.Tweet
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class ComposeActivity : AppCompatActivity () {

    lateinit var setCompose: EditText
    lateinit var buttonTweet: Button
    lateinit var client: TwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        setCompose = findViewById(R.id.editText)
        buttonTweet = findViewById(R.id.buttonTweet)

        client = TwitterApplication.getRestClient(this)
        buttonTweet.setOnClickListener {

            val tweetContent = setCompose.text.toString()

            if (tweetContent.isEmpty()) {
                Toast.makeText(this, "Empty Tweet is not allowed!", Toast.LENGTH_SHORT)
                    .show()

            } else {

                if (tweetContent.length > 140) {
                    Toast.makeText(this, "Tweet is over 140 char long. Limit your tweet!", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    client.postTweet(tweetContent, object: JsonHttpResponseHandler() {
                        override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                            Log.i(TAG, "Successfully published Tweet!")

                            val tweet = Tweet.fromJson(json.jsonObject)

                            val intent = Intent()

                            intent.putExtra("tweet", tweet)
                            setResult(RESULT_OK, intent)
                            finish()
                        }

                        override fun onFailure(
                            statusCode: Int,
                            headers: Headers?,
                            response: String?,
                            throwable: Throwable?,
                        ) {
                            Log.e(TAG, "Failed to post", throwable)
                        }


                    })
                }

            }

        }

    }
    companion object {
        val TAG = "ComposeActivity"
    }
}