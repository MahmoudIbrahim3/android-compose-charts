package com.mahmoud.composecharts

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun dpToPx(value: Dp): Float = LocalDensity.current.run { value.toPx() }

@Composable
fun textUnitToPx(value: TextUnit): Float = LocalDensity.current.run { value.toPx() }

