package com.example.clean_architecture.data.local.prefs.api

import android.net.Uri
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UriDeserializer : JsonDeserializer<Uri?> {
    override fun deserialize(
        src: JsonElement, srcType: Type?,
        context: JsonDeserializationContext?,
    ): Uri {
        return Uri.parse(src.asString)
    }
}