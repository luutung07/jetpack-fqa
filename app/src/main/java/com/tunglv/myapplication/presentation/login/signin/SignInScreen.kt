package com.tunglv.myapplication.presentation.login.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tunglv.myapplication.common.getAppString
import com.tunglv.myapplication.getApplication
import com.tunglv.myapplication.presentation.login.INPUT_TYPE
import com.tunglv.myapplication.presentation.login.LoginViewModel
import com.tunglv.myapplication.presentation.login.component.TextFieldInputComponent
import com.tunglv.jetpackfqa.presentation.widget.component.ButtonPrimaryCompose
import com.tunglv.myapplication.ui.theme.Primary
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tunglv.myapplication.common.STRING_DEFAULT
import com.tunglv.myapplication.presentation.login.ERROR_TYPE
import com.tunglv.myapplication.R

@Composable
@Preview
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {

    var inputAccount = STRING_DEFAULT
    var inputPassword = STRING_DEFAULT

    Surface(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.bg_login_1),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp, bottom = 10.dp),
                text = getAppString(R.string.login),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp)
            )

            TextFieldInputComponent(
                hintText = getAppString(R.string.input_user_name),
                icLeft = R.drawable.ic_user_gray,
                modifier = Modifier.padding(top = 12.dp),
                onTextChange = {
                    inputAccount = it
                }
            )

            if (viewModel.errorAccountState.value != ERROR_TYPE.NO_ERROR) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = when (viewModel.errorAccountState.value) {
                        ERROR_TYPE.EMPTY_ERROR -> getAppString(R.string.empty_account)
                        else -> STRING_DEFAULT
                    },
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                )
            }

            TextFieldInputComponent(
                hintText = getAppString(R.string.input_password),
                icLeft = R.drawable.ic_password_gray,
                icRight = R.drawable.ic_hide_password_gray,
                inputType = INPUT_TYPE.PASSWORD,
                icRightSelect = R.drawable.ic_show_password_gray,
                modifier = Modifier.padding(top = 12.dp),
                maxLength = 50,
                onTextChange = {
                    inputPassword = it
                }
            )

            if (viewModel.errorPassWordState.value != ERROR_TYPE.NO_ERROR) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = when (viewModel.errorPassWordState.value) {
                        ERROR_TYPE.EMPTY_ERROR -> getAppString(R.string.empty_password)
                        ERROR_TYPE.MAX_LENGTH_ERROR -> getAppString(R.string.role_register_password)
                        else -> STRING_DEFAULT
                    },
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                )
            }

            ButtonPrimaryCompose(
                label = getAppString(R.string.register),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    viewModel.validate(inputAccount, inputPassword)
                }
            )

            Text(
                text = getAppString(R.string.option_login),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_login_google),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_login_facebook),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_login_zalo),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontStyle = FontStyle(R.font.svn_regular),
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                ) {
                    append(getAppString(R.string.have_account))
                }
                pushStringAnnotation(
                    tag = " ${getAppString(R.string.login)}",
                    annotation = ""
                )
                withStyle(
                    style = SpanStyle(
                        fontStyle = FontStyle(R.font.svn_regular),
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                ) {
                    append(" ${getAppString(R.string.login)}")
                }
                pop()
            }

            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    val annotations = annotatedString.getStringAnnotations(
                        tag = " ${getAppString(R.string.login)}",
                        start = offset,
                        end = offset
                    )
                    annotations.firstOrNull()?.let { annotation ->
                        Toast.makeText(getApplication(), "dhasdsabdasbhd", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp)
            )
        }
    }
}