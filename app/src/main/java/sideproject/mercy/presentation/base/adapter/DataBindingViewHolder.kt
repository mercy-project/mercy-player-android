package sideproject.mercy.presentation.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T>(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(sideproject.mercy.BR.item, item)
    }
}