package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodPrivateAction extends ChangeVisibilityAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodPrivateAction() {
		super();
	}

	@Override
	protected String getNewToken() {
		return "private";
	}

	@Override
	protected boolean isTokenToReplace(int token) {
		return token == ITerminalSymbols.TokenNameprotected || token == ITerminalSymbols.TokenNamepublic;
	}

	@Override
	protected int getDisableMask() {
		return Flags.AccPrivate;
	}

}
