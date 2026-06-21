package com.neouul.umc10android.week10.presentation.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize

@Composable
fun Modifier.zoomable(
    maxScale: Float = 4f,
    minScale: Float = 1f
): Modifier {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    return this
        .onSizeChanged { size = it }
        .pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = {
                    if (scale > 1f) {
                        scale = 1f
                        offset = Offset.Zero
                    } else {
                        scale = 2.5f
                    }
                }
            )
        }
        .pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, _ ->
                scale = (scale * zoom).coerceIn(minScale, maxScale)

                if (scale > 1f) {
                    val maxOffsetX = (size.width * (scale - 1)) / 2f
                    val maxOffsetY = (size.height * (scale - 1)) / 2f

                    offset = Offset(
                        x = (offset.x + pan.x * scale).coerceIn(-maxOffsetX, maxOffsetX),
                        y = (offset.y + pan.y * scale).coerceIn(-maxOffsetY, maxOffsetY)
                    )
                } else {
                    offset = Offset.Zero
                }
            }
        }
        .graphicsLayer(
            scaleX = scale,
            scaleY = scale,
            translationX = offset.x,
            translationY = offset.y
        )
}
