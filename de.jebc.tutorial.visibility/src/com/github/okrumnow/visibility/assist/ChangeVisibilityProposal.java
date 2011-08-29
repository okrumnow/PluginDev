package com.github.okrumnow.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.internal.corext.dom.ModifierRewrite;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

@SuppressWarnings("restriction")
public abstract class ChangeVisibilityProposal implements IJavaCompletionProposal {

	private final int newVisibility;
	private final String description;
	private final MethodDeclaration declaration;
	private ASTRewrite rewriter;

	public ChangeVisibilityProposal(String description, int newVisibility, MethodDeclaration declaration) {
		this.description = description;
		this.newVisibility = newVisibility;
		this.declaration = declaration;
		setModifier();
	}

	@Override
	public void apply(IDocument document) {
		
		TextEdit textEdits = rewriter.rewriteAST(document, null);
		try {
			textEdits.apply(document);
		} catch (MalformedTreeException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void setModifier() {
		rewriter = ASTRewrite.create(declaration.getAST());
		ModifierRewrite modrewrite = ModifierRewrite.create(rewriter, declaration);
		modrewrite.setVisibility(newVisibility, null);
	}

	@Override
	public String getAdditionalProposalInfo() {
		return null;
	}

	@Override
	public String getDisplayString() {
		return description;
	}

	@Override
	public IContextInformation getContextInformation() {
		return null;
	}

	@Override
	public int getRelevance() {
		return 1;
	}

	@Override
	public Point getSelection(IDocument document) {
		return null;
	}

	@Override
	public Image getImage() {
		return JavaPluginImages.get(JavaPluginImages.IMG_CORRECTION_CHANGE);
	}

}
