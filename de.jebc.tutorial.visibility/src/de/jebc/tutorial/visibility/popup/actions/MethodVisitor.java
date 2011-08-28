package de.jebc.tutorial.visibility.popup.actions;

import org.eclipse.jdt.core.ILocalVariable;
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
		String identifier = node.getName().getIdentifier();
		String elementName = member.getElementName();
		boolean result = false;
		try {
			if (identifier.equals(elementName)) {
				if (member instanceof SourceMethod) {
					SourceMethod method = (SourceMethod) member;
					if (node.parameters().size() == method
							.getNumberOfParameters()) {
						ILocalVariable[] methodParameres = method
								.getParameters();
						result = true;
						for (int i = 0; i < method.getNumberOfParameters(); i++) {
							SingleVariableDeclaration p = (SingleVariableDeclaration) node.parameters().get(i);
							ILocalVariable lv = methodParameres[i];
							String methodParameterType = Signature.toString(lv.getTypeSignature());
							String nodeParameterType = p.getType().toString();
							if (!methodParameterType.equals(nodeParameterType)) {
								result = false;
								break;
							}
						}
					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public MethodDeclaration getMethod() {
		return method;
	}
}