package wottrich.github.io.androidworkshop_crud.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wottrich.github.io.androidworkshop_crud.R
import wottrich.github.io.androidworkshop_crud.model.User

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/08/20
 *
 * Copyright © 2020 AndroidWorkshop-CRUD. All rights reserved.
 *
 */
 
class UserAdapter(
    context: Context,
    private var items: List<User>,
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
): RecyclerView.Adapter<UserViewHolder>() {

    var onDelete: (User) -> Unit = {}
    var onEdit: (User) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(layoutInflater.inflate(R.layout.row_user, parent, false))
    }

    override fun getItemCount(): Int = items.size

    private fun getItem (position: Int) : User? {
        return if (position >= items.size) null else items[position]
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(holder.absoluteAdapterPosition)
        item?.let {
            holder.bind(it, onDelete, onEdit)
        }
    }

    fun setItems (items: List<User>) {
        this.items = items
        notifyDataSetChanged()
    }

}