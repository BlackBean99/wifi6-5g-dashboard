package com.jnu.wifi6.domain.dto

data class ClientData(
    val mac: String,
    val description: String?,
    val ip: String,
    val user: String?,
    val firstSeen: String,
    val lastSeen: String,
    val ssid: String,
    val usageSent: Long,
    val usageRecv: Long,
    val usageTotal: Long,
    val status: String
)