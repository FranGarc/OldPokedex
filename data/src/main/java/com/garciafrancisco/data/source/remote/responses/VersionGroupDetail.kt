package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VersionGroupDetail(
    @Json(name = "level_learned_at") val level_learned_at: Int,
    @Json(name = "move_learn_method") val move_learn_method: MoveLearnMethod,
    @Json(name = "version_group") val version_group: VersionGroup
)