// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:9


package rs.ac.bg.etf.pp1.ast;

public class VarDeclList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclBase VarDeclBase;
    private VarDeclMul VarDeclMul;

    public VarDeclList (Type Type, VarDeclBase VarDeclBase, VarDeclMul VarDeclMul) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclBase=VarDeclBase;
        if(VarDeclBase!=null) VarDeclBase.setParent(this);
        this.VarDeclMul=VarDeclMul;
        if(VarDeclMul!=null) VarDeclMul.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclBase getVarDeclBase() {
        return VarDeclBase;
    }

    public void setVarDeclBase(VarDeclBase VarDeclBase) {
        this.VarDeclBase=VarDeclBase;
    }

    public VarDeclMul getVarDeclMul() {
        return VarDeclMul;
    }

    public void setVarDeclMul(VarDeclMul VarDeclMul) {
        this.VarDeclMul=VarDeclMul;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclBase!=null) VarDeclBase.accept(visitor);
        if(VarDeclMul!=null) VarDeclMul.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclBase!=null) VarDeclBase.traverseTopDown(visitor);
        if(VarDeclMul!=null) VarDeclMul.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclBase!=null) VarDeclBase.traverseBottomUp(visitor);
        if(VarDeclMul!=null) VarDeclMul.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclList(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclBase!=null)
            buffer.append(VarDeclBase.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclMul!=null)
            buffer.append(VarDeclMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclList]");
        return buffer.toString();
    }
}
