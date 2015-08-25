package de.mprengemann.customcheckstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.ScopeUtils;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NamingConventionCheck extends Check {

    private static final String MSG_CONSTANT = "Constant '%s' should be named in all uppercase with underscores.";

    @Override
    public int[] getDefaultTokens() {
        return new int[] {TokenTypes.VARIABLE_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST modifiersAST = ast.findFirstToken(TokenTypes.MODIFIERS);
        final String name = findVariableName(ast);
        if (ScopeUtils.inInterfaceOrAnnotationBlock(ast) ||
            "serialVersionUID".equals(name)) {
            return;
        }

        if (isStaticVariable(modifiersAST) &&
            isFinalVariable(modifiersAST)) {
            if (NamingConventions.isWrongConstantNaming(name)) {
                log(ast.getLineNo(),
                    ast.getColumnNo(),
                    String.format(MSG_CONSTANT, name));
            }
        }
    }

    private boolean isFinalVariable(DetailAST ast) {
        return ast.branchContains(TokenTypes.FINAL);
    }

    private boolean isStaticVariable(DetailAST ast) {
        return ast.branchContains(TokenTypes.LITERAL_STATIC);
    }

    private String findVariableName(DetailAST ast) {
        return ast.findFirstToken(TokenTypes.IDENT).getText();
    }

}
