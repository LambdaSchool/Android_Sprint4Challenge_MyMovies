package com.lambdaschool.datapersistencesprintchallenge
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject

@Entity
class FavouriteMovie{
    companion object {
        const val INVALID_ID = 0
    }

    var title:String?=null
    var isFavourite:Boolean=false

    @PrimaryKey(autoGenerate = true) @NonNull
    var id:Int=0


    constructor(title:String?,isFavourite:Boolean,id:Int){
        this.title=title
        this.isFavourite=isFavourite
        this.id=id
    }
    constructor(csvString:String){
        val values=csvString.split(",")
        this.title=values[0]
        this.isFavourite=values[1].toBoolean()
        this.id=values[2].toInt()
    }
    fun toCsvString():String{
        return "$title,$isFavourite,$id"
    }
    constructor(jsonObject: JSONObject) {
        try {
            this.title = jsonObject.getString("title")
        } catch (e: JSONException) {
            this.title = ""
        }
        try {
            this.isFavourite = jsonObject.getBoolean("has_been_read")
        } catch (e: JSONException) {
            this.isFavourite = false
        }
        try {
            this.id = jsonObject.getString("id").toInt()
        }catch (e: JSONException) {
            this.id = 0
        }
    }
    fun toJsonObject(): JSONObject? {
        try {
            return JSONObject().apply {
                put("title", title)
                put("is_favourite", isFavourite)
                put("id", id)
            }
        } catch (e1: JSONException) {
            return try {
                JSONObject("{\"title\" : \"$title\", \"is_favourite\" : \"$isFavourite\", \"id\" : \"$id\"}")
            } catch (e2: JSONException) {
                e2.printStackTrace()
                return null
            }
        }
    }
}

