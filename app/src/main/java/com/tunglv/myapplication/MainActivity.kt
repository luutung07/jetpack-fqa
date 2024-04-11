package com.tunglv.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tunglv.myapplication.common.navigateSingleTopTo
import com.tunglv.myapplication.presentation.destination.SignInDestination
import com.tunglv.myapplication.presentation.destination.SignUpDestination
import com.tunglv.myapplication.presentation.destination.SplashDestination
import com.tunglv.myapplication.presentation.splash.SplashScreen
import com.tunglv.myapplication.presentation.login.LoginViewModel
import com.tunglv.myapplication.presentation.login.signin.SignInScreen
import com.tunglv.myapplication.presentation.login.signup.SignUpScreen
import com.tunglv.myapplication.ui.theme.JetpackFQATheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackFQATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FqaApp()
                }
            }
        }
    }

    @Composable
    fun FqaApp(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        val currentBackStack by navController.currentBackStackEntryAsState()
        // Fetch your currentDestination:
        val currentDestination = currentBackStack?.destination
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = SplashDestination.route
            ) {
                composable(route = SplashDestination.route) {
                    SplashScreen(
                        modifier = Modifier,
                        navigationTo = {
                            navController.navigateSingleTopTo(SignUpDestination.route)
                        }
                    )
                }
                composable(route = SignUpDestination.route) {
                    SignUpScreen(
                        navigationToSignIn = {
                            navController.navigateSingleTopTo(SignInDestination.route)
                        }
                    )
                }
                composable(route = SignInDestination.route) {
                    SignInScreen(
                        navigationToSignUp = {
                            navController.navigateSingleTopTo(SignUpDestination.route)
                        }
                    )
                }
            }
        }
    }
}


