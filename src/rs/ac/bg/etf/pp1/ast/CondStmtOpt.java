// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class CondStmtOpt extends CondStatementOpt {

    private CondStatement CondStatement;

    public CondStmtOpt (CondStatement CondStatement) {
        this.CondStatement=CondStatement;
        if(CondStatement!=null) CondStatement.setParent(this);
    }

    public CondStatement getCondStatement() {
        return CondStatement;
    }

    public void setCondStatement(CondStatement CondStatement) {
        this.CondStatement=CondStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondStatement!=null) CondStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondStatement!=null) CondStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondStatement!=null) CondStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondStmtOpt(\n");

        if(CondStatement!=null)
            buffer.append(CondStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondStmtOpt]");
        return buffer.toString();
    }
}
