package io.sunland.chainpass.common.component

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VerticalScrollbar(modifier: Modifier, scrollState: ScrollState)