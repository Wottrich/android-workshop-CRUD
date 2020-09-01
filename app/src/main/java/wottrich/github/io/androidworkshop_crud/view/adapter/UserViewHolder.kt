package wottrich.github.io.androidworkshop_crud.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_user.view.*
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(user: User, onDelete: (User) -> Unit = {}, onEdit: (User) -> Unit = {}) {
        view.apply {
            tvName.text = user.name

            ibDelete.setOnClickListener { onDelete(user) }
            ibEdit.setOnClickListener { onEdit(user) }
        }
    }

}