

> How to create Menu in Android Kotlin

Kotlin
```kotlin
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.program_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mu_activity_life_cycle -> {
                startActivity(Intent(this, ActivityLifeCycleActivity::class.java))
            }
            R.id.mu_enter_text_program -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return true
    }
```
XML
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/mu_activity_life_cycle"
        android:title="@string/activity_life_cycle" />
    <item
        android:id="@+id/mu_enter_text_program"
        android:title="@string/edit_text_to_textview" />
</menu>
```


> Async 

* Async Task
```kotlin
        // Calling Async task
        val thread = DownloadData()
        thread.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml")
```



```kotlin
//AsyncTask is 
        /** @deprecated */ 
    companion object {
        //companion object  will help to access method from whole application without any instance
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
        }
    }
```
* XML Parser

```kotlin

class ParseClass {
    private val TAG: String = "ParseClass"
    private val applications = ArrayList<FeedEntity>()

    fun parse(xmlData: String): ArrayList<FeedEntity> {
        Log.d(TAG, "parse called with data $xmlData")
        var status = true
        var inEntity = true
        var textValue = ""
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType
            var currentRecord = FeedEntity()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xpp.name?.toLowerCase()
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (tagName == "entry") {
                            inEntity = true
                        }
                    }
                    XmlPullParser.TEXT -> {
                        textValue = xpp.text
                    }
                    XmlPullParser.END_TAG -> {
                        Log.d(TAG, "parse ending tag $tagName")
                        if (inEntity)
                            when (tagName) {
                                "entry" -> {
                                    applications.add(currentRecord)
                                    inEntity = false
                                    currentRecord = FeedEntity()
                                }
                                "name" -> currentRecord.name = textValue
                                "artist" -> currentRecord.artist = textValue
                                "price" -> currentRecord.price = textValue
                                "releaseDate" -> currentRecord.releaseDate = textValue
                                "image" -> currentRecord.imageURL = textValue
                            }
                    }
                }
                eventType = xpp.next()
            }
            for (app in applications) {
                Log.e(TAG, "***************************")
                Log.e(TAG, app.toString())
            }

        } catch (e: Exception) {
            Log.e(TAG, "ERROR $e")
            status = false
        }
        return applications
    }
}
```


