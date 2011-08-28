package de.jebc.tutorial.visibility.popup.actions;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.JavaModelException;
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
			IDocument document = buffer instanceof IDocument ? (IDocument) buffer : new DocumentAdapter(buffer);
			
			CompilationUnit parser = parse(cu);
			MethodVisitor visitor = new MethodVisitor(member);
			parser.accept(visitor);
			MethodDeclaration declaration = visitor.getMethod();
			
			ASTRewrite rewriter = ASTRewrite.create(parser.getAST());
			ModifierRewrite modrewrite = ModifierRewrite.create(rewriter, declaration);
			modrewrite.setVisibility(Modifier.PUBLIC, null);
			TextEdit textEdits = rewriter.rewriteAST(document, null);
			try {
				textEdits.apply(document);
			} catch (MalformedTreeException e) {
				e.printStackTrace();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			cu.commitWorkingCopy(false, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		member = (IMember) ((IStructuredSelection) selection)
				.getFirstElement();
	}
	
	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}

}
