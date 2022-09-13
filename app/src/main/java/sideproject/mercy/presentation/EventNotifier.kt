package sideproject.mercy.presentation

import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity

interface EventNotifier

/**
 * action event 전송을 위한 인터페이스
 */
interface ActionEventNotifier : EventNotifier {
	fun notifyActionEvent(entity: ActionEntity)
}

/**
 * click event 전송을 위한 인터페이스
 */
interface ClickEventNotifier : EventNotifier {
	fun notifyClickEvent(entity: ClickEntity)
}

interface ClickActionEventNotifier : ClickEventNotifier, ActionEventNotifier