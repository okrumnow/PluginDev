package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class ChangeVisibilityAction {

	private IMember member;

	protected abstract int getDisableMask();

	protected abstract boolean isTokenToReplace(int token);

	protected abstract String getNewToken();

	public ChangeVisibilityAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		ICompilationUnit cu = member.getCompilationUnit();
		try {
			cu.becomeWorkingCopy(null);
			IBuffer buffer = cu.getBuffer();
			IScanner scanner = ToolFactory.createScanner(false, false, false,
					false);
			scanner.setSource(buffer.getCharacters());
			ISourceRange sr = member.getSourceRange();
			scanner.resetTo(sr.getOffset(), sr.getOffset() + sr.getLength() - 1);
	
			int token = scanner.getNextToken();
			boolean isDefault = true;
			while (token != ITerminalSymbols.TokenNameEOF
					&& token != ITerminalSymbols.TokenNameLPAREN) {
				if (isTokenToReplace(token)) {
					int currentTokenStartPosition = scanner.getCurrentTokenStartPosition();
					int currentTokenEndPosition = scanner.getCurrentTokenEndPosition();
					buffer.replace(
							currentTokenStartPosition,
							currentTokenEndPosition
									- currentTokenStartPosition+1,
							getNewToken());
					isDefault = false;
					break;
				}
				token = scanner.getNextToken();
			}
			if (isDefault) {
				buffer.replace(sr.getOffset(),0, getNewToken() + " ");
			}
			cu.commitWorkingCopy(false, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		member = (IMember) ((IStructuredSelection) selection).getFirstElement();
		if (member != null) {
			try {
				action.setEnabled((member.getFlags() & getDisableMask()) == 0);
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		} else action.setEnabled(false);
	}

}