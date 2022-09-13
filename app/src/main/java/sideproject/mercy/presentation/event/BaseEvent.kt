package sideproject.mercy.presentation.event

import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.utils.livedata.LiveHandledData
import sideproject.mercy.utils.livedata.MutableLiveHandledData

typealias ForceQuit = Boolean

interface IBaseEvent {
	/** action event ( toast, dialog ... ) */
	val action: LiveHandledData<ActionEntity>

	/** 화면 요소 선택 이벤트 (= click, touch) */
	var click: LiveHandledData<ClickEntity>

	/** error */
	var throwable: LiveHandledData<Pair<Throwable, ForceQuit>>
}

open class BaseEvent : IBaseEvent {
	val _action: MutableLiveHandledData<ActionEntity> by lazy { MutableLiveHandledData() }
	val _click: MutableLiveHandledData<ClickEntity> by lazy { MutableLiveHandledData() }
	val _throwable: MutableLiveHandledData<Pair<Throwable, Boolean>> by lazy { MutableLiveHandledData() }

	override val action: LiveHandledData<ActionEntity>
		get() = _action

	override var click: LiveHandledData<ClickEntity> =
		_click

	override var throwable: LiveHandledData<Pair<Throwable, Boolean>> =
		_throwable
}