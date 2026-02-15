package com.bovinescan.app

import android.content.Context
import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import kotlinx.coroutines.tasks.await

object CattleGuard {
    private val CATTLE_KEYWORDS = setOf(
        "Cattle", "Cow", "Bull", "Calf", "Livestock", "Ox", "Bovine", "Snout", "Horn"
    )

    private const val MIN_CONFIDENCE = 0.5f

    suspend fun isCattle(bitmap: Bitmap): Boolean {
        return try {
            val image = InputImage.fromBitmap(bitmap, 0)
            val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
            
            val labels = labeler.process(image).await()
            
            labels.any { label ->
                val text = label.text
                val confidence = label.confidence
                // Check if label matches keywords with sufficient confidence
                confidence >= MIN_CONFIDENCE && CATTLE_KEYWORDS.any { keyword -> 
                    text.contains(keyword, ignoreCase = true) 
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // If ML Kit fails, default to allowing check (fail open) vs fail closed?
            // User requested robust guard, so maybe fail closed if critical, 
            // but let's log error and return false to be safe (strict).
            false
        }
    }
}
