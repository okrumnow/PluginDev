package de.jebc.tutorial.visibility.assist;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class ChangeVisibilityToDefaultProposal extends ChangeVisibilityProposal {

	public ChangeVisibilityToDefaultProposal(MethodDeclaration declaration) {
		super("Make '" + declaration.getName().toString() + "' (default)", Modifier.NONE, declaration);
	}

}
