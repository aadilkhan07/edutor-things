package com.aadil.chapteraiscreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aadil.mergeapplication.chapter.ChapterItem
import com.aadil.mergeapplication.chapter.ChapterItemCapsule

/**
 * Displays items in rows of 2:
 *  - LEFT item: 40% of row width
 *  - RIGHT item: 60% of row width
 *  - 2.dp gap between them
 * No content-based sizing here—it's purely based on weights.
 */
@Composable
fun ChapterItemGrid(
    items: List<ChapterItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Break items into pairs
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp) // 2.dp gap
            ) {
                // LEFT item: 40% of row
                ChapterItemCapsule(
                    item = rowItems[0],
                    modifier = Modifier.weight(0.4f)
                )

                // RIGHT item: 60% of row (if present)
                if (rowItems.size > 1) {
                    ChapterItemCapsule(
                        item = rowItems[1],
                        modifier = Modifier.weight(0.6f)
                    )
                } else {
                    // Only one item in this row -> fill leftover space with a spacer
                    Spacer(modifier = Modifier.weight(0.6f))
                }
            }
        }
    }
}
