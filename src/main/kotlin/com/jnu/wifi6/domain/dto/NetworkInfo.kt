package com.jnu.wifi6.domain.dto

data class NetworkInfo(
    val name: String? = null,
    val serial: String? = null,
    val mac: String? = null,
    val networkId: String? = null,
    val productType: String? = null,
    val model: String? = null,
    val address: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val notes: String? = null,
    val tags: List<String>? = null,
    val lanIp: String? = null,
    val configurationUpdatedAt: String? = null,
    val firmware: String? = null,
    val url: String? = null,
) {
}
