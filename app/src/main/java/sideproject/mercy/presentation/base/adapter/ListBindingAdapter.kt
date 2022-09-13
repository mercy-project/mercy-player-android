package sideproject.mercy.presentation.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class ListBindingAdapter<VT : Enum<VT>, VM : IViewTypeGetter<VT>>(
	diffCallback: DiffUtil.ItemCallback<VM>
) : ListAdapter<VM, BaseBindingViewHolder<VM, ViewDataBinding>>(diffCallback) {

	override fun onBindViewHolder(
		viewHolder: BaseBindingViewHolder<VM, ViewDataBinding>,
		position: Int
	) {
		currentList.getOrNull(position)?.let {
			viewHolder.onBind(it, position)
		}
	}

	override fun getItemViewType(position: Int): Int {
		return currentList[position].getViewTypeOrdinal()
	}

	fun addItems(newItems: List<VM>) {
		submitList(newItems)
	}

	fun clear() {
		submitList(emptyList())
	}

	fun removeItem(item: VM) {
		currentList.remove(item)
	}
}