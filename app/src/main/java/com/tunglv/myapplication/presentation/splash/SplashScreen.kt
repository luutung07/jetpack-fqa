package com.tunglv.jetpackfqa.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tunglv.jetpackfqa.R

@Preview
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier.wrapContentSize(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_fqa),
                contentDescription = null,
                modifier = Modifier.size(
                    250.dp, 90.dp
                )
            )
        }
    }
}