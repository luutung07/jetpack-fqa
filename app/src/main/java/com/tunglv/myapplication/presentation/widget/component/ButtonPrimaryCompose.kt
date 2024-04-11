package com.tunglv.jetpackfqa.presentation.widget.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tunglv.myapplication.common.BoxClickableRectRectangleRipple
import com.tunglv.myapplication.ui.theme.Primary

@Composable
fun ButtonPrimaryCompose(
    modifier: Modifier = Modifier,
    label: String,
    @DrawableRes leftIc: Int? = null,
    fontSize: Int = 16,
    textColorEnable: Color = Color.White,
    textColorDisable: Color = Color.Gray,
    onClick: (() -> Unit)? = null,
    isEnable: Boolean = true,
    colorDisable: Color = Color.Gray,
) {
    BoxClickableRectRectangleRipple(
        modifier = modifier,
        onclick = {
            onClick?.invoke()
        },
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(52.dp)
                    .background(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isEnable) Primary else colorDisable
                    )
                    .wrapContentSize(Alignment.Center)

            ) {
                if (leftIc != null) {
                    Image(painter = painterResource(id = leftIc), contentDescription = null)
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = fontSize.sp,
                        color = if (isEnable) textColorEnable else textColorDisable
                    ),
                )
            }
        }
    )
}
