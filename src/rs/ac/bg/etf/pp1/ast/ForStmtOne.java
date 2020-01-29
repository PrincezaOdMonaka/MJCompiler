// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:11


package rs.ac.bg.etf.pp1.ast;

public class ForStmtOne extends ForStmt {

    private DesignatorStmtOpt DesignatorStmtOpt;
    private CondStatementOpt CondStatementOpt;
    private DesignatorStmtOpt DesignatorStmtOpt1;
    private OneOrBlockStatement OneOrBlockStatement;

    public ForStmtOne (DesignatorStmtOpt DesignatorStmtOpt, CondStatementOpt CondStatementOpt, DesignatorStmtOpt DesignatorStmtOpt1, OneOrBlockStatement OneOrBlockStatement) {
        this.DesignatorStmtOpt=DesignatorStmtOpt;
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.setParent(this);
        this.CondStatementOpt=CondStatementOpt;
        if(CondStatementOpt!=null) CondStatementOpt.setParent(this);
        this.DesignatorStmtOpt1=DesignatorStmtOpt1;
        if(DesignatorStmtOpt1!=null) DesignatorStmtOpt1.setParent(this);
        this.OneOrBlockStatement=OneOrBlockStatement;
        if(OneOrBlockStatement!=null) OneOrBlockStatement.setParent(this);
    }

    public DesignatorStmtOpt getDesignatorStmtOpt() {
        return DesignatorStmtOpt;
    }

    public void setDesignatorStmtOpt(DesignatorStmtOpt DesignatorStmtOpt) {
        this.DesignatorStmtOpt=DesignatorStmtOpt;
    }

    public CondStatementOpt getCondStatementOpt() {
        return CondStatementOpt;
    }

    public void setCondStatementOpt(CondStatementOpt CondStatementOpt) {
        this.CondStatementOpt=CondStatementOpt;
    }

    public DesignatorStmtOpt getDesignatorStmtOpt1() {
        return DesignatorStmtOpt1;
    }

    public void setDesignatorStmtOpt1(DesignatorStmtOpt DesignatorStmtOpt1) {
        this.DesignatorStmtOpt1=DesignatorStmtOpt1;
    }

    public OneOrBlockStatement getOneOrBlockStatement() {
        return OneOrBlockStatement;
    }

    public void setOneOrBlockStatement(OneOrBlockStatement OneOrBlockStatement) {
        this.OneOrBlockStatement=OneOrBlockStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.accept(visitor);
        if(CondStatementOpt!=null) CondStatementOpt.accept(visitor);
        if(DesignatorStmtOpt1!=null) DesignatorStmtOpt1.accept(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.traverseTopDown(visitor);
        if(CondStatementOpt!=null) CondStatementOpt.traverseTopDown(visitor);
        if(DesignatorStmtOpt1!=null) DesignatorStmtOpt1.traverseTopDown(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.traverseBottomUp(visitor);
        if(CondStatementOpt!=null) CondStatementOpt.traverseBottomUp(visitor);
        if(DesignatorStmtOpt1!=null) DesignatorStmtOpt1.traverseBottomUp(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmtOne(\n");

        if(DesignatorStmtOpt!=null)
            buffer.append(DesignatorStmtOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondStatementOpt!=null)
            buffer.append(CondStatementOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOpt1!=null)
            buffer.append(DesignatorStmtOpt1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneOrBlockStatement!=null)
            buffer.append(OneOrBlockStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmtOne]");
        return buffer.toString();
    }
}
