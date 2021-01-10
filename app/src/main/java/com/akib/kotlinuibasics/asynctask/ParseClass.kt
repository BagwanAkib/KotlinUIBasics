package com.akib.kotlinuibasics.asynctask

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.lang.Exception

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
//                        Log.d(TAG, tagName!!)
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