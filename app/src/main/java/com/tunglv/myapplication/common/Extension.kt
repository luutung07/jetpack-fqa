package com.tunglv.myapplication.common

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.tunglv.myapplication.getApplication


const val DEFAULT_DEBOUNCE_INTERVAL = 350L
const val TIME_SCROLL_TO_TOP = 200
const val OS_TYPE_ANDROID = 1

internal abstract class DebouncedOnClickListener(
    private val delayBetweenClicks: Long = DEFAULT_DEBOUNCE_INTERVAL
) : View.OnClickListener {
    private var lastClickTimestamp = -1L

    @Deprecated(
        message = "onDebouncedClick should be overridden instead.",
        replaceWith = ReplaceWith("onDebouncedClick(v)")
    )

    override fun onClick(v: View) {
        val now = System.currentTimeMillis()
        if (lastClickTimestamp == -1L || now >= (lastClickTimestamp + delayBetweenClicks)) {
            onDebouncedClick(v)
        }
        lastClickTimestamp = now
    }

    abstract fun onDebouncedClick(v: View)
}

fun View.onSafeClick(listener: View.OnClickListener?) {
    listener?.let {
        this.onDebouncedClick { _ ->
            it.onClick(this)
        }
    }
}

fun View.onDebouncedClick(
    delayBetweenClicks: Long = DEFAULT_DEBOUNCE_INTERVAL,
    click: (view: View) -> Unit
) {
    setOnClickListener(object : DebouncedOnClickListener(delayBetweenClicks) {
        override fun onDebouncedClick(v: View) = click(v)
    })
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.clickedInside(event: MotionEvent): Boolean {
    return clickedInside(event.rawX, event.rawY)
}

fun View.clickedInside(rowX: Float, rowY: Float): Boolean {
    val loc = this.getLocationPositionOnScreen()
    return (rowX >= loc[0])
            && (rowX <= loc[0] + width)
            && (rowY >= loc[1])
            && (rowY <= loc[1] + height)
}

fun View.getLocationPositionOnScreen(): IntArray {
    val loc = IntArray(2)
    this.getLocationOnScreen(loc)
    return loc
}

fun TextView.setImageTextView(
    left: Drawable? = null,
    top: Drawable? = null,
    right: Drawable? = null,
    bottom: Drawable? = null
) {
    setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
}

fun getAppString(@StringRes stringId: Int, context: Context? = getApplication()): String {
    return context?.getString(stringId) ?: ""
}

fun getAppString(
    @StringRes resId: Int,
    vararg formatArgs: Any?,
    context: Context? = getApplication()
): String {
    return context?.getString(resId, *formatArgs) ?: ""
}

fun getAppFont(@FontRes fontId: Int, context: Context? = getApplication()): Typeface? {
    context?.let {
        return ResourcesCompat.getFont(context, fontId)
    }
    return null
}

fun getAppDrawable(@DrawableRes drawableId: Int, context: Context? = getApplication()): Drawable? {
    if (context == null) {
        return null
    }
    return ContextCompat.getDrawable(context, drawableId)
}

fun getAppDimensionPixel(@DimenRes dimenId: Int, context: Context? = getApplication()) =
    context?.resources?.getDimensionPixelSize(dimenId) ?: -1

fun getAppDimension(@DimenRes dimenId: Int, context: Context? = getApplication()) =
    context?.resources?.getDimension(dimenId) ?: -1f

fun getAppColor(@ColorRes colorRes: Int, context: Context? = getApplication()) =
    context?.let { ContextCompat.getColor(it, colorRes) } ?: Color.TRANSPARENT

@Stable
@Composable
fun BoxClickableRectRectangleRipple(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    onclick: (() -> Unit)?,
) {
    Box(
        modifier = modifier.clickable(
            onClick = {
                onclick?.invoke()
            },
            indication = rememberRipple(
                bounded = true
            ),
            interactionSource = remember { MutableInteractionSource() }
        ),
    ) {
        content.invoke(this)
    }
}

const val STRING_DEFAULT = ""
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
