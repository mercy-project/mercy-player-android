package sideproject.mercy.utils.extensions

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import sideproject.mercy.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("image_url")
    fun setImageUrl(
        view: ImageView,
        imageUrl: String?
    ) {
        Glide.with(view)
            .load(imageUrl)
            .placeholder(R.drawable.ic_book)
            .centerInside()
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("goneUnless")
    fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["onBackPressed"])
    fun bindOnBackPressed(view: Toolbar, onBackPressed: Boolean) {
        if (onBackPressed) {
            view.setNavigationOnClickListener {
                when(it.context) {
                    is Activity -> {
                        (it.context as? Activity)?.onBackPressed()
                    }

                    is Fragment -> {
                        (it.context as? Fragment)?.findNavController()?.popBackStack()
                    }
                }
            }
        }
    }
}