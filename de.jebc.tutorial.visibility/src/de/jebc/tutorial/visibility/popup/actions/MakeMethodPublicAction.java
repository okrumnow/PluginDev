package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.ui.IObjectActionDelegate;

public class MakeMethodPublicAction extends ChangeVisibilityAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodPublicAction() {
		super();
	}

	@Override
	protected String getNewToken() {
		return "public";
	}

	@Override
	protected boolean isTokenToReplace(int token) {
		return token == ITerminalSymbols.TokenNameprivate;
	}

	@Override
	protected int getDisableMask() {
		return Flags.AccPublic;
	}

}
