package com.tunglv.myapplication.presentation.login.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tunglv.myapplication.presentation.login.INPUT_TYPE
import com.tunglv.myapplication.ui.theme.GrayLight
import com.tunglv.myapplication.ui.theme.GrayMedium
import com.tunglv.myapplication.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldInputComponent(
    modifier: Modifier = Modifier,
    hintText: String,
    @DrawableRes icLeft: Int,
    @DrawableRes icRight: Int? = null,
    @DrawableRes icRightSelect: Int? = null,
    inputType: INPUT_TYPE = INPUT_TYPE.NORMAL,
    onTextChange: ((String) -> Unit)? = null,
    maxLength: Int = 1000
) {
    var textInput by remember { mutableStateOf("") }
    var hasFocus by remember { mutableStateOf(false) }
    var isSeePassword by remember { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(56.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = if (hasFocus) Primary else GrayMedium
                )
                .onFocusChanged {
                    hasFocus = it.hasFocus
                },
            value = textInput,
            onValueChange = {
                if (it.length <= maxLength) {
                    textInput = it
                    onTextChange?.invoke(it)
                }
            },
            placeholder = {
                Text(
                    text = hintText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = GrayLight
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(icLeft),
                    contentDescription = null,
                    tint = if (hasFocus) Color.Gray else GrayLight
                )
            },
            trailingIcon = {
                if (icRight != null && icRightSelect != null) {
                    Icon(
                        imageVector = if (isSeePassword) {
                            ImageVector.vectorResource(icRightSelect)
                        } else ImageVector.vectorResource(
                            icRight
                        ),
                        contentDescription = null,
                        tint = if (hasFocus) Color.Gray else GrayLight,
                        modifier = Modifier.clickable {
                            isSeePassword = !isSeePassword
                        }
                    )
                }
            },
            visualTransformation = if (inputType == INPUT_TYPE.PASSWORD) {
                if (isSeePassword) VisualTransformation.None else PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Primary,
                disabledLabelColor = Primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp,
                color = Color.Black
            ),
            maxLines = 1
        )
    }
}


