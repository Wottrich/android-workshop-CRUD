package wottrich.github.io.androidworkshop_crud.archive

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 27/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

fun Activity.hideKeyboard(windowToken: IBinder?) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}