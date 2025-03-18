// ! Bu araç @keyiflerolsun tarafından | @KekikAkademi için yazılmıştır.

package com.keyiflerolsun

import android.util.Log
import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.fasterxml.jackson.annotation.JsonProperty

open class TauVideo : ExtractorApi() {
    override val name            = "TauVideo"
    override val mainUrl         = "https://tau-video.xyz"
    override val requiresReferer = true

    override suspend fun getUrl(url: String, referer: String?, subtitleCallback: (SubtitleFile) -> Unit, callback: (ExtractorLink) -> Unit) {
        val extRef   = referer ?: ""
        val videoKey = url.substringAfterLast("/").substringBefore(".")
        val videoUrl = "$mainUrl/api/video/$videoKey"
        Log.d("Kekik_${this.name}", "videoUrl » $videoUrl")

        val api = app.get(videoUrl).parsedSafe<TauVideoUrls>() ?: throw Exception("TauVideo API'den veri alınamadı.")

        for (video in api.urls) {
            callback.invoke(
                ExtractorLink(
                    source  = this.name,
                    name    = this.name,
                    url     = video.url,
                    referer = extRef,
                    quality = getQualityFromName(video.label),
                    type    = INFER_TYPE
                )
            )
        }
    }

    data class TauVideoUrls(
        @JsonProperty("urls") val urls: List<TauVideoData>
    )

    data class TauVideoData(
        @JsonProperty("url")   val url: String,
        @JsonProperty("label") val label: String,
    )
}