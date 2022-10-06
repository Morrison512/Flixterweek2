package com.example.flixterp2ar

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class Flix (
    @SerializedName("name")
    var nameTitle: String?,

    @SerializedName("profile_path")
    var profilePath: String?,

    @SerializedName("known_for")
    var knownFor: List<KnownFor>?
) : java.io.Serializable

@Keep
@Serializable
data class KnownFor(
    @SerializedName("original_title")
    var title: String?,

    @SerializedName("original_name")
    var name: String?,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("overview")
    var flixDescription: String?
) : java.io.Serializable