package com.github.okrumnow.visibility.popup.actions;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.Flags;
import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodPublicAction extends ChangeVisibilityAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodPublicAction() {
		super();
	}

	@Override
	protected int getNewModifier() {
		return Modifier.PUBLIC;
	}

	@Override
	protected int getDisableMask() {
		return Flags.AccPublic;
	}

}
