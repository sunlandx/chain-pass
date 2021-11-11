package io.sunland.chainpass.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.typesafe.config.ConfigFactory
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.sunland.chainpass.common.App
import io.sunland.chainpass.common.SocketConnectionType
import java.util.*

fun main(args: Array<String>) = application {
    val config = args.joinToString { "application.$it" }.ifEmpty { "application" }.let {
        ConfigFactory.load(it).getConfig("client")
    }

    val httpClient = HttpClient(CIO) {
        install(WebSockets)
        install(Logging)

        defaultRequest {
            method = HttpMethod.Get
            host = config.getString("host")
            port = config.getInt("port")

            url {
                protocol = URLProtocol.byName[config.getString("protocol")]!!
            }

            header("Socket-Type", SocketConnectionType.CLIENT)
            header("Socket-Id", UUID.randomUUID().toString())
        }
    }

    Window(title = "Chain Pass", onCloseRequest = ::exitApplication) {
        App(httpClient)
    }
}
