package com.example.mvvc_todo.util

import android.annotation.SuppressLint
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mvvc_todo.R

@BindingAdapter ("android:aturWarna")
fun aturWarna(view: CheckBox, kategori:Int ){
    when(kategori){
        0 -> {
            view.setBackgroundResource(R.drawable.custom_business)
        }
       1 -> {
           view.setBackgroundResource(R.drawable.custom_personal)
       }
    }
}
