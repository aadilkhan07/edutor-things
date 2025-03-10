package com.aadil.mergeapplication.question

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.aadil.mergeapplication.question.QuestionScreen

import com.aadil.mergeapplication.ui.theme.MergeApplicationTheme

/**
 * AnimatedItem wraps any composable so that it animates into view.
 * When animateVertically is false (the default), it slides in horizontally from the left.
 * When animateVertically is true, it slides in vertically from above,
 * starting completely hidden (0 opacity and off-screen) and then drops in.
 * If enabled is false, no animation is applied.
 */
@Composable
fun AnimatedItem(
    modifier: Modifier = Modifier,
    delayMillis: Int = 0,
    enabled: Boolean = true,
    animateVertically: Boolean = false,
    content: @Composable () -> Unit
) {
    if (!enabled) {
        Box(modifier = modifier) { content() }
    } else {
        if (animateVertically) {
            // New vertical animation: start with content completely hidden above.
            val dropDistancePx = with(LocalDensity.current) { 200.dp.toPx() }
            var startAnimation by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { startAnimation = true }
            // Animate translationY from -dropDistance (completely hidden above) to 0.
            val animTranslationY by animateFloatAsState(
                targetValue = if (startAnimation) 0f else -dropDistancePx,
                animationSpec = tween(
                    durationMillis = 150, // Very fast drop
                    delayMillis = delayMillis,
                    easing = FastOutSlowInEasing
                )
            )
            // Also animate alpha from 0 to 1.
            val animAlpha by animateFloatAsState(
                targetValue = if (startAnimation) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 150,
                    delayMillis = delayMillis,
                    easing = FastOutSlowInEasing
                )
            )
            Box(
                modifier = modifier.graphicsLayer {
                    translationY = animTranslationY
                    alpha = animAlpha
                }
            ) {
                content()
            }
        } else {
            // Horizontal animation: slide in from left (off-screen using screen width)
            val configuration = LocalConfiguration.current
            val screenWidthPx = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }
            var startAnimation by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { startAnimation = true }
            val animTranslationX by animateFloatAsState(
                targetValue = if (startAnimation) 0f else -screenWidthPx,
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = delayMillis,
                    easing = FastOutSlowInEasing
                )
            )
            Box(
                modifier = modifier.graphicsLayer {
                    translationX = animTranslationX
                }
            ) {
                content()
            }
        }
    }
}

@Composable
fun QuestionScreen(
    questions: List<Question>,
    historyItems: List<HistoryItem>
) {
    val scrollState = rememberScrollState()
    var showAll by remember { mutableStateOf(false) }
    // Show first 4 or all questions based on state.
    val displayedQuestions = if (showAll) questions else questions.take(4)
    // For sequential animation: assign a delay (e.g. 100ms per item).
    val delayPerItem = 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Display question boxes in rows (2 per row)
        for (i in displayedQuestions.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // For each question, if showAll is true and the item is newly loaded (index ≥ 4),
                // use vertical animation (drop from above).
                AnimatedItem(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    delayMillis = i * delayPerItem,
                    animateVertically = showAll && (i >= 4)
                ) {
                    QuestionBox(question = displayedQuestions[i])
                }
                if (i + 1 < displayedQuestions.size) {
                    AnimatedItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        delayMillis = (i + 1) * delayPerItem,
                        animateVertically = showAll && ((i + 1) >= 4)
                    ) {
                        QuestionBox(question = displayedQuestions[i + 1])
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Toggle button ("More Questions" / "Less")
        if (questions.size > 4) {
            Spacer(modifier = Modifier.height(8.dp))
            // Toggle text always uses horizontal animation.
            AnimatedItem(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                delayMillis = (displayedQuestions.size) * delayPerItem
            ) {
                if (!showAll) {
                    Text(
                        text = "More Questions↓",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2175DF),
                        modifier = Modifier.clickable { showAll = true }
                    )
                } else {
                    Text(
                        text = "Less↑",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2175DF),
                        modifier = Modifier.clickable { showAll = false }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // For the History section, we keep the original animation.
        val baseDelayForHistory = (displayedQuestions.size + 1) * delayPerItem
        HistorySection(historyItems = historyItems, baseDelay = baseDelayForHistory, delayPerItem = delayPerItem)
    }
}

/**
 * A single question box with title, description, and prashna label below it.
 */
@Composable
fun QuestionBox(question: Question, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xFFF5F7F7))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = question.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = question.description,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = question.prashnaNumber,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

/**
 * Displays an animated History header and animated cards for each HistoryItem.
 */
@Composable
fun HistorySection(historyItems: List<HistoryItem>, baseDelay: Int, delayPerItem: Int) {
    AnimatedItem(delayMillis = baseDelay) {
        Text(
            text = "History",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
    historyItems.forEachIndexed { index, item ->
        AnimatedItem(delayMillis = baseDelay + (index + 1) * delayPerItem) {
            Card(
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFF5F1F1),
                                    Color(0xFFECF5EE)
                                )
                            )
                        )
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = item.chapterName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.stdSubjectTime,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

// ----------- Previews ------------
@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    MergeApplicationTheme  {
        val questions = listOf(
            Question("prashna 1", "Title 1", "Description 1"),
            Question("prashna 2", "Title 2", "Description 2"),
            Question("prashna 3", "Title 3", "Description 3"),
            Question("prashna 4", "Title 4", "Description 4"),
            Question("prashna 5", "Title 5", "Description 5"),
            Question("prashna 6", "Title 6", "Description 6"),
            Question("prashna 7", "Title 7", "Description 7"),
            Question("prashna 8", "Title 8", "Description 8"),
            Question("prashna 9", "Title 9", "Description 9"),
            Question("prashna 10", "Title 10", "Description 10")
        )

        val historyItems = listOf(
            HistoryItem(
                chapterName = "પ્રશ્ન 4 : વનસ્પતિ-ની ڄاڻકારી મેળવીએ",
                stdSubjectTime = "Std 7 - Maths - 2 days ago"
            ),
            HistoryItem(
                chapterName = "प्रश्न 5 : Photons & Light",
                stdSubjectTime = "Std 8 - Science - 1 day ago"
            ),
            HistoryItem(
                chapterName = "Question 6 : Chemical Reactions",
                stdSubjectTime = "Std 9 - Science - 5 hours ago"
            )
        )
        QuestionScreen(questions, historyItems)
    }
}
