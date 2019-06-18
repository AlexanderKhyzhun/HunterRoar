package com.yotalabs.hunterroar.utils

import android.content.Context
import android.util.TypedValue

/**
 * @author Alexander Khyzhun
 * Created on 18 June, 2019
 */

fun getFontSize(ctx: Context, textAppearance: Int): Float {
    val typedValue = TypedValue()
    ctx.theme.resolveAttribute(textAppearance, typedValue, true)
    val textSizeAttr = intArrayOf(android.R.attr.textSize)
    val arr = ctx.obtainStyledAttributes(typedValue.data, textSizeAttr)
    val fontSize = arr.getDimensionPixelSize(0, -1).toFloat()
    arr.recycle()
    return fontSize
}