package com.valentinerutto.datacacheroom.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.valentinerutto.datacacheroom.utils.ConnectionState
import com.valentinerutto.datacacheroom.utils.currentConnectivityState
import com.valentinerutto.datacacheroom.utils.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    // Creates a State<ConnectionState> with current connectivity state as initial value
    return produceState(initialValue = context.currentConnectivityState) {
        // In a coroutine, can make suspend calls
        context.observeConnectivityAsFlow().collect { value = it }
    }
}