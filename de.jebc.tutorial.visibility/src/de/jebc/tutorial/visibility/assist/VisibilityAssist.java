package de.jebc.tutorial.visibility.assist;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.IProblemLocation;
import org.eclipse.jdt.ui.text.java.IQuickAssistProcessor;

public class VisibilityAssist implements IQuickAssistProcessor {

	@Override
	public boolean hasAssists(IInvocationContext context) throws CoreException {
		return false;
	}

	@Override
	public IJavaCompletionProposal[] getAssists(IInvocationContext context,
			IProblemLocation[] locations) throws CoreException {
		ASTNode coveringNode = context.getCoveringNode();
		MethodDeclaration declaration = findDeclaration(coveringNode);
		if (declaration == null) return null;
		return new IJavaCompletionProposal[] {};
	}

	private MethodDeclaration findDeclaration(ASTNode coveringNode) {
		MethodDeclaration result = null;
		if (coveringNode instanceof MethodDeclaration)
			result = (MethodDeclaration) coveringNode;
		else if (coveringNode.getParent() instanceof MethodDeclaration)
			result = (MethodDeclaration) coveringNode.getParent();
		return result;
	}

}
