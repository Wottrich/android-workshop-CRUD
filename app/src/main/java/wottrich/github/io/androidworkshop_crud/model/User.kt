package wottrich.github.io.androidworkshop_crud.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
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

@Entity(tableName = "user")
@Parcelize
data class User(
    @ColumnInfo(name = "item_id")
    @SerializedName("id")
    @PrimaryKey
    val id: Long,
    val name: String
) : Parcelable