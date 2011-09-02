package org.github.okrumnow.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class ChangeVisibilityToProtectedProposal extends
		ChangeVisibilityProposal {

	public ChangeVisibilityToProtectedProposal(MethodDeclaration declaration) {
		super("Make '" + declaration.getName().toString() + "' protected", Modifier.PROTECTED, declaration);
	}

}
