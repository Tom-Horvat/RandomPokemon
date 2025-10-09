package io.bitbot.bemusedbaboon.common.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import io.bitbot.bemusedbaboon.common.api.PokemonIndexResponse

class PokeIndexJasonAdapter(moshi: Moshi) {
    private val adapter: JsonAdapter<PokemonIndexResponse> =
        moshi.adapter(PokemonIndexResponse::class.java)

    @FromJson
    fun fromJson(json: String?): PokemonIndexResponse? = json?.let { adapter.fromJson(json) }

    @ToJson
    fun toJson(index: PokemonIndexResponse): String = adapter.toJson(index)
}