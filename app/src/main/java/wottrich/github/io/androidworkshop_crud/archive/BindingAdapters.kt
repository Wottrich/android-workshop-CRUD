package wottrich.github.io.androidworkshop_crud.archive

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 27/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
 object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}