package de.jebc.tutorial.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class ChangeVisibilityToPublicProposal extends ChangeVisibilityProposal {

	public ChangeVisibilityToPublicProposal(MethodDeclaration declaration) {
		super("Make '" + declaration.getName().toString() + "' public", "public", declaration);
	}

}
