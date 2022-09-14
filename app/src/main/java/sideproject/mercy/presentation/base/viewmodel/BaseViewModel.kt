package sideproject.mercy.presentation.base.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ClickActionEventNotifier
import sideproject.mercy.presentation.base.event.BaseEvent
import sideproject.mercy.presentation.base.event.IBaseEvent

abstract class BaseViewModel : ViewModel(), ClickActionEventNotifier {

	protected open val _event: BaseEvent = BaseEvent()
	open val event: IBaseEvent
		get() = _event

	val isLoading = ObservableBoolean(false)

	protected open fun handleActionEvent(entity: ActionEntity) {}

	override fun notifyActionEvent(entity: ActionEntity) {
		handleActionEvent(entity)
		_event._action.setHandledValue(entity)
	}

	protected open fun handleClickEvent(entity: ClickEntity) {}
	override fun notifyClickEvent(entity: ClickEntity) {
		handleClickEvent(entity)
		_event._click.setHandledValue(entity)
	}

	override fun onCleared() {
		super.onCleared()

		hideLoading()
	}

	fun showLoading() {
		isLoading.set(true)
	}

	fun hideLoading() {
		isLoading.set(false)
	}
}