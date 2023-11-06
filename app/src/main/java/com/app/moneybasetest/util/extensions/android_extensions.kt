package com.app.moneybasetest.util.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

inline fun <T : ViewBinding> AppCompatDialog.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

inline fun <T : ViewBinding> ViewGroup.viewBinding(
        crossinline bindingInflater: (LayoutInflater, ViewGroup) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater(LayoutInflater.from(context), this)
        }