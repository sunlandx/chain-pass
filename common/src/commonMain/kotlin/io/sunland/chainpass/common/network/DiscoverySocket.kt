package io.sunland.chainpass.common.network

object SocketConfig {
    const val DNS = "8.8.8.8"
    const val HOST = "0.0.0.0"
    const val PORT = 8888
    const val MESSAGE = "DISCOVERY"
    const val TIMEOUT = 250
}

expect object DiscoverySocket {
    fun getLocalHost(): String
    suspend fun discover(): String
}