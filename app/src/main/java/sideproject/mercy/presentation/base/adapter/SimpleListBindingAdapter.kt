package sideproject.mercy.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class SimpleListBindingAdapter<T>(
	diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding =
			DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
		return DataBindingViewHolder(binding)
	}

	final override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
		viewBindViewHolder(holder, position)
		holder.bind(getItem(position))
	}

	protected open fun viewBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {}

	fun addItems(newItems: List<T>) {
		submitList(newItems)
	}

	fun clear() {
		submitList(emptyList())
	}

	fun removeItem(item: T) {
		currentList.remove(item)
	}
}