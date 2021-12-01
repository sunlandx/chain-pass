package io.sunland.chainpass.common.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.sunland.chainpass.common.ChainLink

@Composable
fun ChainLinkListItemEdit(chainLink: ChainLink, onIconDoneClick: () -> Unit, onIconClearClick: () -> Unit) {
    val passwordState = remember { mutableStateOf(chainLink.password.value) }
    val passwordErrorState = remember { mutableStateOf(!chainLink.password.isValid) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(modifier = Modifier.padding(all = 16.dp), text = chainLink.name.value)
            Row {
                IconButton(onClick = {
                    val chainLinkPassword = ChainLink.Password(passwordState.value)

                    passwordErrorState.value = !chainLinkPassword.isValid

                    if (!passwordErrorState.value) {
                        chainLink.password = chainLinkPassword

                        onIconDoneClick()
                    }
                }) { Icon(imageVector = Icons.Default.Done, contentDescription = null) }
                IconButton(onClick = {
                    passwordState.value = chainLink.password.value
                    passwordErrorState.value = !chainLink.password.isValid

                    onIconClearClick()
                }) { Icon(imageVector = Icons.Default.Clear, contentDescription = null) }
            }
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Password") },
            value = passwordState.value,
            onValueChange = { password ->
                val chainLinkPassword = ChainLink.Password(password)

                passwordState.value = chainLinkPassword.value
                passwordErrorState.value = !chainLinkPassword.isValid
            },
            trailingIcon = if (passwordErrorState.value) {
                { Icon(imageVector = Icons.Default.Info, contentDescription = null) }
            } else null,
            isError = passwordErrorState.value,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )
    }
}
