package android.test.com.didemo.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.test.com.didemo.models.User
import android.view.View
import android.view.ViewGroup

class UserAdapter: ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldUser: User, newUser: User) = oldUser.id == newUser.id
            override fun areContentsTheSame(oldUser: User, newUser: User) = oldUser == newUser
        }
    }


}
