package de.jebc.tutorial.visibility.popup.actions;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.Flags;
import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodProtectedAction extends ChangeVisibilityAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodProtectedAction() {
		super();
	}

	@Override
	protected int getNewModifier() {
		return Modifier.PROTECTED;
	}

	@Override
	protected int getDisableMask() {
		return Flags.AccProtected;
	}

}
