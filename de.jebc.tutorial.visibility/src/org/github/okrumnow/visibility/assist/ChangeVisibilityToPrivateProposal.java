package org.github.okrumnow.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class ChangeVisibilityToPrivateProposal extends ChangeVisibilityProposal {

	public ChangeVisibilityToPrivateProposal(MethodDeclaration declaration) {
		super("Make '" + declaration.getName().toString() + "' private", Modifier.PRIVATE, declaration);
	}

}
