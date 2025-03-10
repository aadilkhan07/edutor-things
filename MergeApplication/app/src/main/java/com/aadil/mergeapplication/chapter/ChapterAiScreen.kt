package com.aadil.mergeapplication.chapter

import com.aadil.mergeapplication.chapter.CapsuleItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aadil.chapteraiscreen.ChapterItemGrid
import com.aadil.mergeapplication.R
import com.aadil.mergeapplication.common.ChapterTopBar

@Composable
fun ChapterAIScreen(
    firstName: String,
    modifier: Modifier = Modifier
) {
    // Example data
    val chapterItems = listOf(
        ChapterItem(R.drawable.ic_launcher_background, "ટોપિક લીસ્ટ"),
        ChapterItem(R.drawable.ic_launcher_background, "સ્વઅભ્યાસ પ્રશ્ન"),
        ChapterItem(R.drawable.ic_launcher_background, "કલ્પના ?"),
        ChapterItem(R.drawable.ic_launcher_background, "વધુ જાણવાના કેવું")
    )

    val capsuleTexts = listOf(
        "પાઠ યાદુ કરતા પડીલાની જરૂરી બાબત સમજાવો",
        "પાઠમાંથી IMP પ્રશ્નો આપો",
        "પાઠનું રિવિઝન करवાવો",
        "પાઠની સારાંશ સમજાવો",
        "મહત્ત્વની તમામ વ્યાખ્યાઓ"
    )

    // Top-level Column
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        // 1) Our new top bar
        ChapterTopBar(
            title = "ભાગ 1 - પ્રકરણ 1 : સંબંધી...", // Or any text you want
            onBackClick = { /* TODO: Handle back navigation */ },
            onStackClick = { /* TODO: Handle stack icon click */ }
        )

        // 2) Rest of the content
        Column(
            modifier = Modifier
                .weight(1f)                 // Fill remaining vertical space
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            // "Hello"
            Spacer(modifier = Modifier.height(16.dp)) // Space below the top bar
            Text(
                text = "Hello",
                style = TextStyle(
                    color = Color(0xFF6F2491),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // firstName
            Text(
                text = firstName,
                style = TextStyle(
                    color = Color(0xFF6F2491),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Image & Gujarati text horizontally centered
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background1),
                    contentDescription = "AI Chapter Image",
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .aspectRatio(1f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "AI આ પ્રશ્ન સંબંધીત દરેક બાળકને\n" +
                            "એકદમ સરળ રીતે સમજવામાં મદદ કરશે",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Grid of ChapterItems
            ChapterItemGrid(
                items = chapterItems,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Column of capsule texts
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                capsuleTexts.forEach { text ->
                    CapsuleItem(text = text)
                }
            }
        }
    }
}
