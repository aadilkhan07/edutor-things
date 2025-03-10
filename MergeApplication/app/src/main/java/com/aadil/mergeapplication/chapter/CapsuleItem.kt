package com.aadil.mergeapplication.chapter

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A reusable capsule composable with a transparent background
 * and a black border.
 *
 * @param text The text displayed inside the capsule.
 * @param modifier Optional [Modifier] for further customization.
 * @param borderColor Color of the capsule's border.
 * @param shape Shape of the capsule. Defaults to a pill-like shape.
 */
@Composable
fun CapsuleItem(
    text: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Black,
    shape: Shape = RoundedCornerShape(50)
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            // Make the capsule fill its parent's width, so all capsules match in size.
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )
    }
}
