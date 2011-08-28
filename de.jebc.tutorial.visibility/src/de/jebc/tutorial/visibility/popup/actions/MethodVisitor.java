package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodVisitor extends ASTVisitor {
	
	private MethodDeclaration method;
	private final IMember member;
	
	public MethodVisitor(IMember member) {
		this.member = member;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		String identifier = node.getName().getIdentifier();
		String elementName = member.getElementName();
		if (identifier.equals(elementName)) {
			method = node;
		}
		return super.visit(node);
	}

	public MethodDeclaration getMethod() {
		return method;
	}
}
