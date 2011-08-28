package de.jebc.tutorial.visibility.popup.actions;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.internal.core.DocumentAdapter;
import org.eclipse.jdt.internal.corext.dom.ModifierRewrite;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

@SuppressWarnings("restriction")
public class MakeMethodPublicAction implements IObjectActionDelegate {

	private Shell shell;
	private IMember member;

	/**
	 * Constructor for Action1.
	 */
	public MakeMethodPublicAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
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
			while (token != ITerminalSymbols.TokenNameEOF
					&& token != ITerminalSymbols.TokenNameLPAREN) {
				if (token == ITerminalSymbols.TokenNameprivate) {
					int currentTokenStartPosition = scanner.getCurrentTokenStartPosition();
					int currentTokenEndPosition = scanner.getCurrentTokenEndPosition();
					buffer.replace(
							currentTokenStartPosition,
							currentTokenEndPosition
									- currentTokenStartPosition+1,
							"public");
					break;
				}
				token = scanner.getNextToken();
			}
			cu.commitWorkingCopy(false, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
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
				action.setEnabled((member.getFlags() & Flags.AccPublic) == 0);
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		} else action.setEnabled(false);
	}

	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}

}
