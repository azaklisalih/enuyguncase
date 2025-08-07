package com.example.enuyguncase.presentation.common.binding

import com.example.enuyguncase.R

import android.annotation.SuppressLint
import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.enuyguncase.domain.model.Dimensions

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        } else {
            view.setImageDrawable(null)
        }
    }

    @SuppressLint("DefaultLocale")
    @JvmStatic
    @BindingAdapter("formattedPrice")
    fun formatPrice(view: TextView, price: Double?) {
        view.text = if (price != null) {
            String.format(view.context.getString(R.string.currency_format), price)
        } else {
            ""
        }
    }

    @SuppressLint("DefaultLocale")
    @JvmStatic
    @BindingAdapter("formattedDiscountPrice")
    fun formatDiscountPrice(view: TextView, amount: Double?) {
        view.text = amount?.let { String.format(view.context.getString(R.string.currency_format), it) } ?: ""
    }

    // ui/binding/BindingAdapters.kt
    @BindingAdapter("strikeThrough")
    @JvmStatic
    fun strikeThrough(textView: TextView, strike: Boolean) {
        textView.paintFlags = if (strike)
            textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else
            textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }

}
