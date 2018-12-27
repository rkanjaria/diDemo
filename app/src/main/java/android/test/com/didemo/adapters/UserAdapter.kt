package android.test.com.didemo.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.test.com.didemo.R
import android.test.com.didemo.models.User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.user_recyler_layout.view.*

class UserAdapter(val glide: RequestManager) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_recyler_layout, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(getItem(position))
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView = itemView
        fun bindUser(item: User?) {
            mView.userName.text = "${item?.name?.first} ${item?.name?.last}"
            mView.userEmail.text = item?.email
            mView.userAge.text = "${item?.dob?.age} years old"

            glide.load(item?.picture?.large)
                    .apply(RequestOptions().centerCrop())
                    .apply(RequestOptions().circleCrop())
                    .apply(RequestOptions().error(R.color.material_grey_300))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mView.userImage)
        }

    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldUser: User, newUser: User) = oldUser.email == newUser.email
            override fun areContentsTheSame(oldUser: User, newUser: User) = oldUser == newUser
        }
    }
}
