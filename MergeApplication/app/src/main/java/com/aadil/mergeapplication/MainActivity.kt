package com.aadil.mergeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aadil.mergeapplication.chapter.ChapterAIScreen
import com.aadil.mergeapplication.question.HistoryItem
import com.aadil.mergeapplication.question.Question
import com.aadil.mergeapplication.question.QuestionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Sample data for the QuestionScreen
                val questions = listOf(
                    Question("prashna 1", "એકસ્પ્લેનેશન", "ચોક્કસ! અહીં問題Samjhayche"),
                    Question("prashna 2", "એકસ્પ્લેન ગી", "હું વધુ વિશદ了解Samajhno. તમે"),
                    Question("prashna 3", "એકસ્પ્લેન ટાઇટલ", "મહે ક્રરો,标题 'ટાઇટલ' Samajhe अर्थ samajh pao"),
                    Question("prashna 4", "એકસ્પ્લેન મેં", "મહે ક્રરો, પણ હું了解Samajh નથી samajh पा रहा"),
                    Question("prashna 5", "એકસ્પ્લેન 5", "Some more data"),
                    Question("prashna 6", "એકસ્પ્લેન 6", "Some more data"),
                    Question("prashna 7", "એકસ્પ્લેન 7", "Some more data"),
                    // Add as many as needed...
                )
                val historyItems = listOf(
                    HistoryItem(
                        chapterName = "પ્રશ્ન 4 : વનસ્પતિ-ની ڄાણકારી મેળવીએ",
                        stdSubjectTime = "Std 7 - Maths - 2 days ago"
                    ),
                    HistoryItem(
                        chapterName = "પ્રશ્ન 5 : Photons & Light",
                        stdSubjectTime = "Std 8 - Science - 1 day ago"
                    ),
                    // Add as many as needed...
                )

                // We'll toggle between the Chapter screen and the Question screen
                var showChapterScreen by remember { mutableStateOf(true) }

                Column(modifier = Modifier.fillMaxSize()) {
                    // Simple row with two buttons to switch screens
                    Row(modifier = Modifier.padding(16.dp)) {
                        Button(onClick = { showChapterScreen = true }) {
                            Text("Chapter Screen")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = { showChapterScreen = false }) {
                            Text("Question Screen")
                        }
                    }

                    // Display the selected screen
                    if (showChapterScreen) {
                        // From your first project's MainActivity logic
                        ChapterAIScreen(firstName = "Daksha")
                    } else {
                        // From your second project's MainActivity logic
                        QuestionScreen(questions, historyItems)
                    }
                }
            }
        }
    }
}
