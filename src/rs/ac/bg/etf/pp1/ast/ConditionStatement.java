// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConditionStatement extends Statement {

    private CondStatement CondStatement;
    private OneOrBlockStatement OneOrBlockStatement;
    private ElseStatement ElseStatement;

    public ConditionStatement (CondStatement CondStatement, OneOrBlockStatement OneOrBlockStatement, ElseStatement ElseStatement) {
        this.CondStatement=CondStatement;
        if(CondStatement!=null) CondStatement.setParent(this);
        this.OneOrBlockStatement=OneOrBlockStatement;
        if(OneOrBlockStatement!=null) OneOrBlockStatement.setParent(this);
        this.ElseStatement=ElseStatement;
        if(ElseStatement!=null) ElseStatement.setParent(this);
    }

    public CondStatement getCondStatement() {
        return CondStatement;
    }

    public void setCondStatement(CondStatement CondStatement) {
        this.CondStatement=CondStatement;
    }

    public OneOrBlockStatement getOneOrBlockStatement() {
        return OneOrBlockStatement;
    }

    public void setOneOrBlockStatement(OneOrBlockStatement OneOrBlockStatement) {
        this.OneOrBlockStatement=OneOrBlockStatement;
    }

    public ElseStatement getElseStatement() {
        return ElseStatement;
    }

    public void setElseStatement(ElseStatement ElseStatement) {
        this.ElseStatement=ElseStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondStatement!=null) CondStatement.accept(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.accept(visitor);
        if(ElseStatement!=null) ElseStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondStatement!=null) CondStatement.traverseTopDown(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.traverseTopDown(visitor);
        if(ElseStatement!=null) ElseStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondStatement!=null) CondStatement.traverseBottomUp(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.traverseBottomUp(visitor);
        if(ElseStatement!=null) ElseStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionStatement(\n");

        if(CondStatement!=null)
            buffer.append(CondStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneOrBlockStatement!=null)
            buffer.append(OneOrBlockStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseStatement!=null)
            buffer.append(ElseStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionStatement]");
        return buffer.toString();
    }
}
