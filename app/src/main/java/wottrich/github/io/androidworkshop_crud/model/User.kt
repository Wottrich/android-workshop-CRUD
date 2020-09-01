package wottrich.github.io.androidworkshop_crud.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.math.BigInteger

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */

@Parcelize
data class User (
    val id: BigInteger,
    val name: String
): Parcelable