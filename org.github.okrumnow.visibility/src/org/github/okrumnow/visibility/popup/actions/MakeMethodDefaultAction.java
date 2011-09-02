package org.github.okrumnow.visibility.popup.actions;

import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodDefaultAction extends ChangeVisibilityAction implements
		IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodDefaultAction() {
		super();
	}

	@Override
	protected int getNewModifier() {
		return 0;
	}

	@Override
	protected int getDisableMask() {
		return 0;
	}
}
