package com.aadil.mergeapplication.chapter



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChapterItemCapsule(
    item: ChapterItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            // If you want a subtle left-to-right gradient, uncomment below:
//            .background(
//                brush = Brush.horizontalGradient(
//                    listOf(
//                        Color(0xFFE7F7E7), // Light green-ish
//                        Color.White       // Transitions to white
//                    )
//                ),
//                shape = RoundedCornerShape(20.dp)
//            )
            // If you prefer a single color instead of a gradient, use:

            .background(
                color = Color(0xFFECEFF1),
                shape = RoundedCornerShape(12.dp)
            )

            // Adjust horizontal/vertical padding to match your exact design
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon (left)
        Image(
            painter = painterResource(id = item.iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp) // Adjust icon size as needed
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Text (right)
        Text(
            text = item.title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
