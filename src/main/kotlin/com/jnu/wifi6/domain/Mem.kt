package com.jnu.wifi6.domain

import com.influxdb.annotations.Column
import com.influxdb.annotations.Measurement
import java.time.Instant

@Measurement(name = "mem")
data class Mem(
    @Column(tag = true) val host: String,
    @Column val used_percent: Double,
    @Column(timestamp = true) val time: Instant,
)
