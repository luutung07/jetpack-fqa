package com.tunglv.myapplication.presentation.destination

abstract class Destination {
    abstract val route: String
}

object SplashDestination : Destination() {
    override val route: String
        get() = SplashDestination::class.java.simpleName
}

object SignUpDestination : Destination() {
    override val route: String
        get() = SignUpDestination::class.java.simpleName
}

object SignInDestination : Destination() {
    override val route: String
        get() = SignInDestination::class.java.simpleName
}
