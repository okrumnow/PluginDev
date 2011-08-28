package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodProtectedAction extends ChangeVisibilityAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodProtectedAction() {
		super();
	}

	@Override
	protected String getNewToken() {
		return "protected";
	}

	@Override
	protected boolean isTokenToReplace(int token) {
		return token == ITerminalSymbols.TokenNameprivate || token == ITerminalSymbols.TokenNamepublic;
	}

	@Override
	protected int getDisableMask() {
		return Flags.AccProtected;
	}

}
