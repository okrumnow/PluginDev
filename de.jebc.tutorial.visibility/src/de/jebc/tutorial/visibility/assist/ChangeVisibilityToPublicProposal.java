package de.jebc.tutorial.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.ui.text.java.IInvocationContext;

public class ChangeVisibilityToPublicProposal extends ChangeVisibilityProposal {

	public ChangeVisibilityToPublicProposal(MethodDeclaration declaration, IInvocationContext context) {
		super("Make '" + declaration.getName().toString() + "' public", Modifier.PUBLIC, declaration, context);
	}

}
