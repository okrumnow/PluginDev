package de.jebc.tutorial.visibility.popup.actions;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.Flags;
import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodPrivateAction extends ChangeVisibilityAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodPrivateAction() {
		super();
	}

	@Override
	protected int getNewModifier() {
		return Modifier.PRIVATE;
	}

	@Override
	protected int getDisableMask() {
		return Flags.AccPrivate;
	}

}
