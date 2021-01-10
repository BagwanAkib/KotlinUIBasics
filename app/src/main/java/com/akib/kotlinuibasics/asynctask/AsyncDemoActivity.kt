package com.akib.kotlinuibasics.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.TextView
import com.akib.kotlinuibasics.R
import java.net.URL

class AsyncDemoActivity : AppCompatActivity(), AsyncDemoInterface {
    private val TAG: String = "AsyncDemoActivity";
    var txtData: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate start")
        setContentView(R.layout.activity_async_demo)
        txtData = findViewById(R.id.txt_data)
        txtData?.movementMethod = ScrollingMovementMethod()
        val thread = DownloadData()
        thread.interfaceObject = this
        thread.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml")
        Log.e(TAG, "onCreate complete")
    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            val TAG: String = "DownloadData";
            var interfaceObject: AsyncDemoInterface? = null
            override fun doInBackground(vararg url: String?): String {
                Log.e(TAG, "doInBackground")
                return downloadXML(url[0]!!)
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.e(TAG, "onPostExecute result> $result")
                val parse = ParseClass()
                interfaceObject?.onSuccess(parse.parse(result!!))
            }

            // Below is kotlin code to read URL data
            //it is working because it is same code kotlin\io\ReadWrite.kt
            private fun downloadXML(urlPath: String): String {
                return URL(urlPath).readText()
            }
/*
// Below is traditional way to read data from URL
            private fun downloadXML(urlPath: String): String {
                val xml = StringBuilder()
                try {
                    val url = URL(urlPath)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val respondCode = connection.responseCode
                    Log.e(TAG, "Response from URL $respondCode")
                    *//*
                     previously with JAVA we need to do some steps like this

                     val inputStream = connection.inputStream
                     val inputStreamReader = InputStreamReader(inputStream)
                     val reader = BufferedReader(inputStreamReader)

                     val inputBuffer = CharArray(500)
                     var charRead = 0
                     while (charRead >= 0) {
                         charRead = reader.read(inputBuffer)
                         if (charRead > 0) {
                             xml.append(String(inputBuffer, 0, charRead))
                         }
                     }
                     reader.close()
                     inputStream.close()
                     inputStreamReader.close()*//*

                    // to read the data we can use below ReadWrite line 114 having the same code given above to run below code
                    val streamConnection = connection.inputStream
                    streamConnection.buffered().reader().use { reader ->
                        xml.append(reader.readText())
                    }
                    return xml.toString()
                } catch (e: MalformedURLException) {
                    Log.e(TAG, "MalformedURLException ERROR $e")
                } catch (e: IOException) {
                    Log.e(TAG, "IOException ERROR ${e.message}")
                } catch (e: Exception) {
                    Log.e(TAG, "Exception ERROR ${e.message}")
                }
                return ""
            }*/
        }
    }

    override fun onSuccess(records: ArrayList<FeedEntity>) {
        txtData?.text = ""
        for (data in records) {
            txtData?.append("------------------ \n")
            txtData?.append("Name: ${data.name} \n")
            txtData?.append("Artist: ${data.artist} \n")
            txtData?.append("Price: ${data.price} \n")
            txtData?.append("Image URL: ${data.imageURL} \n")
        }
    }

    override fun onFail(message: String) {
        TODO("Not yet implemented")
    }

}