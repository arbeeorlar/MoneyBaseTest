package com.app.moneybasetest.util.networkconnection



sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}