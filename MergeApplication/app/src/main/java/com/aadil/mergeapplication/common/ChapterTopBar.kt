package com.aadil.mergeapplication.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.aadil.mergeapplication.R

@Composable
fun ChapterTopBar(
    title: String,
    onBackClick: () -> Unit,
    onStackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp) // Adjust if you want a different height
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Arrow
        IconButton(onClick = onBackClick) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // replace with your actual icon
                contentDescription = "Back Arrow",
                modifier = Modifier.size(24.dp)
            )
        }

        // Title
        Text(
            text = title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        // Stack Icon
        IconButton(onClick = onStackClick) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // replace with your actual icon
                contentDescription = "Stack Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
