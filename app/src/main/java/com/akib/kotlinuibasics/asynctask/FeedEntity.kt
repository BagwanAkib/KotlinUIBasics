package com.akib.kotlinuibasics.asynctask

class FeedEntity {
    var name: String = ""
    var artist: String = ""
    var releaseDate: String = ""
    var price: String = ""
    var imageURL: String = ""
    override fun toString(): String {
        return "FeedEntity(name='$name', artist='$artist', releaseDate='$releaseDate', price='$price', imageURL='$imageURL')"
    }
}