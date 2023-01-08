package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Move(
    @Json(name = "move")   val move: MoveX,
    @Json(name = "version_group_details")  val version_group_details: List<VersionGroupDetail>
)