package de.jebc.tutorial.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

@SuppressWarnings("restriction")
public abstract class ChangeVisibilityProposal implements IJavaCompletionProposal {

	private final String newVisibility;
	private final String description;

	public ChangeVisibilityProposal(String description, String newVisibility, MethodDeclaration declaration) {
		this.description = description;
		this.newVisibility = newVisibility;
	}

	@Override
	public void apply(IDocument document) {
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
