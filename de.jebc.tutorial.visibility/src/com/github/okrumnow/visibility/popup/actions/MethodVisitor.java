package com.github.okrumnow.visibility.popup.actions;

import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.internal.core.SourceMethod;

@SuppressWarnings("restriction")
public class MethodVisitor extends ASTVisitor {

	private MethodDeclaration method;
	private final IMember member;

	public MethodVisitor(IMember member) {
		this.member = member;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		if (isMatchingDeclaration(node)) {
			method = node;
		}
		return super.visit(node);
	}

	private boolean isMatchingDeclaration(MethodDeclaration node) {
		boolean result = false;
		try {
			if (areNamesIdentical(node)) {
				if (member instanceof SourceMethod) {
					SourceMethod method = (SourceMethod) member;
					if (isParameterCountIdentical(node, method)) {
						result = areParameterTypesIdentical(node, method);
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean areParameterTypesIdentical(MethodDeclaration node,
			SourceMethod method) throws JavaModelException {
		boolean result = true;
		for (int i = 0; i < method.getNumberOfParameters(); i++) {
			if (!parameterTypeIsIdentical(node, method, i)) {
				result = false;
				break;
			}
		}
		return result;
	}

	private boolean parameterTypeIsIdentical(MethodDeclaration node,
			SourceMethod method, int i) throws JavaModelException {
		String typeSignature = method.getParameters()[i].getTypeSignature();
		String nodeSignature = ((SingleVariableDeclaration) node.parameters()
				.get(i)).getType().toString();
		return Signature.toString(typeSignature).equals(nodeSignature);
	}

	private boolean isParameterCountIdentical(MethodDeclaration node,
			SourceMethod method) {
		return node.parameters().size() == method.getNumberOfParameters();
	}

	private boolean areNamesIdentical(MethodDeclaration node) {
		return node.getName().getIdentifier().equals(member.getElementName());
	}

	public MethodDeclaration getMethod() {
		return method;
	}
}