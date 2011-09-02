package org.github.okrumnow.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class ChangeVisibilityToPublicProposal extends ChangeVisibilityProposal {

	public ChangeVisibilityToPublicProposal(MethodDeclaration declaration) {
		super("Make '" + declaration.getName().toString() + "' public", Modifier.PUBLIC, declaration);
	}

}
