package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.compiler.ITerminalSymbols;
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
	protected String getNewToken() {
		return "";
	}

	@Override
	protected boolean isTokenToReplace(int token) {
		return token == ITerminalSymbols.TokenNameprivate || token == ITerminalSymbols.TokenNamepublic || token == ITerminalSymbols.TokenNameprotected;
	}

	@Override
	protected int getDisableMask() {
		return 0;
	}
}
