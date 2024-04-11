package com.tunglv.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.tunglv.myapplication.R

val fqaFamily = FontFamily(
    Font(R.font.svn_bold, FontWeight.Bold),
    Font(R.font.svn_regular, FontWeight.Normal),
    Font(R.font.svn_medium, FontWeight.Medium),
    Font(R.font.svn_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.svn_semi_bold, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fqaFamily,
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = TextStyle(
        fontFamily = fqaFamily,
        fontWeight = FontWeight.Medium,
    ),
    bodySmall = TextStyle(
        fontFamily = fqaFamily,
        fontWeight = FontWeight.Normal,
    ),
)
