// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:9


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationMul extends VarDeclMul {

    private VarDeclMul VarDeclMul;
    private VarDeclBase VarDeclBase;

    public VarDeclarationMul (VarDeclMul VarDeclMul, VarDeclBase VarDeclBase) {
        this.VarDeclMul=VarDeclMul;
        if(VarDeclMul!=null) VarDeclMul.setParent(this);
        this.VarDeclBase=VarDeclBase;
        if(VarDeclBase!=null) VarDeclBase.setParent(this);
    }

    public VarDeclMul getVarDeclMul() {
        return VarDeclMul;
    }

    public void setVarDeclMul(VarDeclMul VarDeclMul) {
        this.VarDeclMul=VarDeclMul;
    }

    public VarDeclBase getVarDeclBase() {
        return VarDeclBase;
    }

    public void setVarDeclBase(VarDeclBase VarDeclBase) {
        this.VarDeclBase=VarDeclBase;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclMul!=null) VarDeclMul.accept(visitor);
        if(VarDeclBase!=null) VarDeclBase.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclMul!=null) VarDeclMul.traverseTopDown(visitor);
        if(VarDeclBase!=null) VarDeclBase.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclMul!=null) VarDeclMul.traverseBottomUp(visitor);
        if(VarDeclBase!=null) VarDeclBase.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationMul(\n");

        if(VarDeclMul!=null)
            buffer.append(VarDeclMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclBase!=null)
            buffer.append(VarDeclBase.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationMul]");
        return buffer.toString();
    }
}
